package bisq.bonded_roles;

import java.util.Set;

// FIXME
// TODO (Critical) Production key not set yet - we use devMode key only yet
public class AuthorizedPubKeys {

    public static final Set<String> SECURITY_MANAGER_PUB_KEYS = Set.of(
    );

    public static final Set<String> RELEASE_MANAGER_PUB_KEYS = Set.of(
    );

    public static final Set<String> MODERATOR_PUB_KEYS = Set.of(
    );

    public static final Set<String> ORACLE_NODE_PUB_KEYS = Set.of(
            // bond holder name: alvasw; profileId: c448de53ab54bec01729ffb82bf141ec235f20de
            "3059301306072a8648ce3d020106082a8648ce3d030107034200040618b364d70fc7b38e6e249e4dbf4a665d3cf1b3578b32ce8f6e9606de376052d5b3b329a93e8f3ad15039ef67edf31e9b0e008961c751bbaf38fadd958975f6",
            // bond holder name: HenrikJannsen; profileId: 13f3c162bb13b84c6b8b40527ec1ea089b9337cd
            "3056301006072a8648ce3d020106052b8104000a034200044c383a7cb3aa24771a477dcb1217452abe382d73c7f9e48bfe424f673706079bb18c52cd6c6281202157e182685131e859aa15fdc32181a5328a5fece99bf92d"
    );

    // Not used yet, but maintain it for later usage
    public static final Set<String> SEED_NODE_PUB_KEYS = Set.of(
            // bond holder name: alvasw; profileId: c448de53ab54bec01729ffb82bf141ec235f20de
            "3059301306072a8648ce3d020106082a8648ce3d030107034200040618b364d70fc7b38e6e249e4dbf4a665d3cf1b3578b32ce8f6e9606de376052d5b3b329a93e8f3ad15039ef67edf31e9b0e008961c751bbaf38fadd958975f6",
            // bond holder name: HenrikJannsen; profileId: 13f3c162bb13b84c6b8b40527ec1ea089b9337cd
            "3056301006072a8648ce3d020106052b8104000a034200044c383a7cb3aa24771a477dcb1217452abe382d73c7f9e48bfe424f673706079bb18c52cd6c6281202157e182685131e859aa15fdc32181a5328a5fece99bf92d"
    );


    /**
     * Dev keys for authorized data
     * privatekey1=30818d020100301006072a8648ce3d020106052b8104000a0476307402010104205b4479d165652fe5410419b1d03c937956be0e1c4f46e9fbe86c66776529d81ca00706052b8104000aa144034200043dd1f2f56593e62670282c245cb71d50b43985b308dd1c977632c3cde155427e4fad0899d7e7af110584182f7e55547d6e1469705567124a02ae2e8afa8e8091
     * publicKey1=3056301006072a8648ce3d020106052b8104000a034200043dd1f2f56593e62670282c245cb71d50b43985b308dd1c977632c3cde155427e4fad0899d7e7af110584182f7e55547d6e1469705567124a02ae2e8afa8e8091
     * <p>
     * privatekey2=30818d020100301006072a8648ce3d020106052b8104000a0476307402010104201c6685cef332eecfddb253b601c0ed89e7c822d2d03c53d5a720a860b5dc9419a00706052b8104000aa144034200044b3a5f869a4a4346fb37ca70991ff55f3ef4f0ed3a31b51238aee75858af48fddaeafb11b9c59b3e9f551ee9022a767e5f39dcea2b4a96e1c610a5f1b1a1878e
     * publicKey2=3056301006072a8648ce3d020106052b8104000a034200044b3a5f869a4a4346fb37ca70991ff55f3ef4f0ed3a31b51238aee75858af48fddaeafb11b9c59b3e9f551ee9022a767e5f39dcea2b4a96e1c610a5f1b1a1878e
     * <p>
     * privatekey3=30818d020100301006072a8648ce3d020106052b8104000a04763074020101042032ead243887ad39f4e1e6907c8e3e6a786eeb6e93323c2fb5a0adb1779e6d4f4a00706052b8104000aa14403420004fbbd94944049f0904d4a92d4dd13dcdce602d676eeafa0049f0181c1815fea24c01226f3a204c985132069dbac92ecadb454ead8c8f2059f7f60a0c3767a5b83
     * publicKey3=3056301006072a8648ce3d020106052b8104000a03420004fbbd94944049f0904d4a92d4dd13dcdce602d676eeafa0049f0181c1815fea24c01226f3a204c985132069dbac92ecadb454ead8c8f2059f7f60a0c3767a5b83
     * <p>
     * privatekey4=30818d020100301006072a8648ce3d020106052b8104000a04763074020101042010c2ea3b2b1f1787f8a57d074e550b120cc04b326b43c545214434e474e5cde2a00706052b8104000aa14403420004170a828efbaa0316b7a59ec5a1e8033ca4c215b5e58b17b16f3e3cbfa5ec085f4bdb660c7b766ec5ba92b432265ba3ed3689c5d87118fbebe19e92b9228aca63
     * publicKey4=3056301006072a8648ce3d020106052b8104000a03420004170a828efbaa0316b7a59ec5a1e8033ca4c215b5e58b17b16f3e3cbfa5ec085f4bdb660c7b766ec5ba92b432265ba3ed3689c5d87118fbebe19e92b9228aca63
     */
    public static final Set<String> DEV_PUB_KEYS = Set.of(
            "3056301006072a8648ce3d020106052b8104000a034200043dd1f2f56593e62670282c245cb71d50b43985b308dd1c977632c3cde155427e4fad0899d7e7af110584182f7e55547d6e1469705567124a02ae2e8afa8e8091", "3056301006072a8648ce3d020106052b8104000a034200044b3a5f869a4a4346fb37ca70991ff55f3ef4f0ed3a31b51238aee75858af48fddaeafb11b9c59b3e9f551ee9022a767e5f39dcea2b4a96e1c610a5f1b1a1878e", "3056301006072a8648ce3d020106052b8104000a03420004fbbd94944049f0904d4a92d4dd13dcdce602d676eeafa0049f0181c1815fea24c01226f3a204c985132069dbac92ecadb454ead8c8f2059f7f60a0c3767a5b83", "3056301006072a8648ce3d020106052b8104000a03420004170a828efbaa0316b7a59ec5a1e8033ca4c215b5e58b17b16f3e3cbfa5ec085f4bdb660c7b766ec5ba92b432265ba3ed3689c5d87118fbebe19e92b9228aca63"
    );
}
