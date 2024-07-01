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

package bisq.desktop.main.content.bisq_easy.open_trades;

import bisq.chat.ChatChannel;
import bisq.chat.ChatMessage;
import bisq.desktop.main.content.chat.message_container.list.ChatMessageListItem;
import bisq.desktop.main.content.chat.message_container.list.ChatMessagesListController;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.fxmisc.easybind.EasyBind;
import org.fxmisc.easybind.Subscription;

public class MyProtocolLogMessageBox extends PeerProtocolLogMessageBox {
    private final Subscription shouldShowTryAgainPin, messageDeliveryStatusNodePin;

    public MyProtocolLogMessageBox(ChatMessageListItem<? extends ChatMessage, ? extends ChatChannel<? extends ChatMessage>> item,
                                   ChatMessagesListController controller) {
        super(item);

        Label tryAgainLabel = item.getTryAgainStatus();
        HBox deliveryStateHBox = new HBox();
        HBox messageStatusHbox = new HBox(5, tryAgainLabel, deliveryStateHBox);

        messageDeliveryStatusNodePin = EasyBind.subscribe(item.getMessageDeliverStatusNode(), node -> {
            deliveryStateHBox.setManaged(node != null);
            deliveryStateHBox.setVisible(node != null);
            if (node != null) {
                deliveryStateHBox.getChildren().setAll(node);
            }
        });

        shouldShowTryAgainPin = EasyBind.subscribe(item.getShouldShouldTryAgain(), showTryAgain -> {
            tryAgainLabel.setVisible(showTryAgain);
            tryAgainLabel.setManaged(showTryAgain);
            if (showTryAgain) {
                tryAgainLabel.setOnMouseClicked(e -> controller.onResendMessage(item.getMessageId()));
            } else {
                tryAgainLabel.setOnMouseClicked(null);
            }
        });

        dateTimeHBox.getChildren().add(0, messageStatusHbox);
    }

    @Override
    public void cleanup() {
        shouldShowTryAgainPin.unsubscribe();
        messageDeliveryStatusNodePin.unsubscribe();
    }
}
