/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package bisq.desktop.main.content.bisq_easy.open_trades.trade_state.states;

import bisq.account.payment_method.BitcoinPaymentRail;
import bisq.bonded_roles.explorer.ExplorerService;
import bisq.chat.bisqeasy.open_trades.BisqEasyOpenTradeChannel;
import bisq.common.data.Pair;
import bisq.desktop.ServiceProvider;
import bisq.desktop.common.Browser;
import bisq.desktop.components.overlay.Popup;
import bisq.desktop.main.content.bisq_easy.open_trades.trade_state.OpenTradesUtils;
import bisq.i18n.Res;
import bisq.offer.Direction;
import bisq.presentation.formatters.DateFormatter;
import bisq.presentation.formatters.PriceFormatter;
import bisq.trade.bisq_easy.BisqEasyTrade;
import bisq.trade.bisq_easy.BisqEasyTradeUtils;
import bisq.user.profile.UserProfile;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

// TODO: Abstract SellerState4 and BuyerState4 since they are the same
@Slf4j
public class SellerState4 extends BaseState {
    private final Controller controller;

    public SellerState4(ServiceProvider serviceProvider, BisqEasyTrade bisqEasyTrade, BisqEasyOpenTradeChannel channel) {
        controller = new Controller(serviceProvider, bisqEasyTrade, channel);
    }

    public View getView() {
        return controller.getView();
    }

    private static class Controller extends BaseState.Controller<Model, View> {
        private final ExplorerService explorerService;

        private Controller(ServiceProvider serviceProvider, BisqEasyTrade bisqEasyTrade, BisqEasyOpenTradeChannel channel) {
            super(serviceProvider, bisqEasyTrade, channel);

            explorerService = serviceProvider.getBondedRolesService().getExplorerService();
        }

        @Override
        protected Model createModel(BisqEasyTrade bisqEasyTrade, BisqEasyOpenTradeChannel channel) {
            return new Model(bisqEasyTrade, channel);
        }

        @Override
        protected View createView() {
            return new View(model, this);
        }

        @Override
        public void onActivate() {
            super.onActivate();

            BitcoinPaymentRail paymentRail = model.getBisqEasyTrade().getContract().getBaseSidePaymentMethodSpec().getPaymentMethod().getPaymentRail();
            String name = paymentRail.name();
            model.setPaymentProofDescription(Res.get("bisqEasy.tradeState.paymentProof." + name));
            model.setBlockExplorerLinkVisible(paymentRail == BitcoinPaymentRail.MAIN_CHAIN);
            String paymentProof = model.getBisqEasyTrade().getPaymentProof().get();
            model.setPaymentProof(paymentProof);
            model.setPaymentProofVisible(paymentProof != null);
            model.setTradePeer(model.getChannel().getPeer());
            model.setDirection(model.getBisqEasyOffer().getDirection());
            model.setFiatCurrency(model.getBisqEasyTrade().getOffer().getMarket().getQuoteCurrencyCode());
            model.setPaymentMethod(model.getBisqEasyTrade().getContract().getQuoteSidePaymentMethodSpec().getShortDisplayString());
            model.setTradeId(model.getBisqEasyTrade().getShortId());
            model.setTradeDate(DateFormatter.formatDate(model.getBisqEasyTrade().getContract().getTakeOfferDate()));
            model.setPrice(PriceFormatter.format(BisqEasyTradeUtils.getPriceQuote(model.getBisqEasyTrade())));
            model.setPriceSymbol(model.getBisqEasyOffer().getMarket().getMarketCodes());
        }

        @Override
        public void onDeactivate() {
            super.onDeactivate();
        }

        private void onLeaveChannel() {
            new Popup().feedback(Res.get("bisqEasy.openTrades.closeTrade.warning.completed"))
                    .actionButtonText(Res.get("bisqEasy.openTrades.confirmCloseTrade"))
                    .onAction(() -> {
                        bisqEasyTradeService.removeTrade(model.getBisqEasyTrade());
                        leavePrivateChatManager.leaveChannel(model.getChannel());
                    })
                    .closeButtonText(Res.get("action.cancel"))
                    .show();
        }

        private void onExportTrade() {
            OpenTradesUtils.exportTrade(model.getBisqEasyTrade(), getView().getRoot().getScene());
        }

        private void openExplorer() {
            ExplorerService.Provider provider = explorerService.getSelectedProvider().get();
            String url = provider.getBaseUrl() + provider.getTxPath() + model.getPaymentProof();
            Browser.open(url);
        }
    }

    @Setter
    @Getter
    private static class Model extends BaseState.Model {
        protected String paymentProof;
        protected String paymentProofDescription;
        protected boolean blockExplorerLinkVisible;
        protected boolean paymentProofVisible;
        protected UserProfile tradePeer;
        protected Direction direction;
        protected String fiatCurrency;
        protected String paymentMethod;
        protected String tradeId;
        protected String tradeDate;
        protected String price;
        protected String priceSymbol;
        protected Optional<String> txId = Optional.empty();

        protected Model(BisqEasyTrade bisqEasyTrade, BisqEasyOpenTradeChannel channel) {
            super(bisqEasyTrade, channel);
        }
    }

    public static class View extends BaseState.View<Model, Controller> {
        private final Button leaveButton, exportButton;
        private final TradeCompletedTable tradeCompletedTable;

        private View(Model model, Controller controller) {
            super(model, controller);

            tradeCompletedTable = new TradeCompletedTable();

            exportButton = new Button(Res.get("bisqEasy.tradeState.info.phase4.exportTrade"));
            leaveButton = new Button(Res.get("bisqEasy.tradeState.info.phase4.leaveChannel"));
            leaveButton.setDefaultButton(true);
            HBox buttons = new HBox(20, exportButton, leaveButton);
            buttons.setAlignment(Pos.BOTTOM_RIGHT);
            VBox.setMargin(buttons, new Insets(0, 0, 20, 0));

            VBox content = new VBox(10, tradeCompletedTable, buttons);
            content.setMaxWidth(1160);
            root.getChildren().addAll(content);
            root.setAlignment(Pos.CENTER);
        }

        @Override
        protected void onViewAttached() {
            super.onViewAttached();

            Optional<Pair<String, String>> txIdDescriptionAndValue = Optional.empty();
            if (model.isPaymentProofVisible()) {
                txIdDescriptionAndValue = Optional.of(new Pair<>(model.getPaymentProofDescription(), model.getPaymentProof()));
            }
            tradeCompletedTable.initialize(model.getTradePeer(), model.getDirection(), model.getBaseAmount(),
                    model.getQuoteAmount(), model.getFiatCurrency(), model.getPaymentMethod(), model.getTradeId(),
                    model.getTradeDate(), model.getPrice(), model.getPriceSymbol(), txIdDescriptionAndValue);
            if (model.isBlockExplorerLinkVisible()) {
                tradeCompletedTable.showBlockExplorerLink();
                tradeCompletedTable.getBlockExplorerButton().setOnAction(e -> controller.openExplorer());
            }
            leaveButton.setOnAction(e -> controller.onLeaveChannel());
            exportButton.setOnAction(e -> controller.onExportTrade());
        }

        @Override
        protected void onViewDetached() {
            super.onViewDetached();

            tradeCompletedTable.dispose();
            leaveButton.setOnAction(null);
            exportButton.setOnAction(null);
        }
    }
}
