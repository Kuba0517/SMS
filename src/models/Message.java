package models;

public class Message {
    private String recipient;
    private String content;
    private String pdu;
    private static final String DEFAULT_SMSC = "00";
    private static final String SUBMIT_SMS = "11";
    private static final String NO_SPECIFIC_PROTOCOL = "00";
    private static final String STANDARD_GSM_ALPHABET = "00";

    public Message(String content) {
        this.content = content;
    }

    private String toHexadecimal(int number) {
        char[] hexadecimalChars = new char[2];
        hexadecimalChars[0] = Character.forDigit((number >> 4) & 0xF, 16);
        hexadecimalChars[1] = Character.forDigit(number & 0xF, 16);
        return new String(hexadecimalChars).toUpperCase();
    }

    public String pduEncode(String msg, String receiver) {
        String receiverAddress = encodeAddress(receiver);
        String userData = encodeMsg(msg);

        String pduSize = toHexadecimal(
                (SUBMIT_SMS + receiverAddress + NO_SPECIFIC_PROTOCOL + STANDARD_GSM_ALPHABET + userData).length() / 2);

        return DEFAULT_SMSC + pduSize + SUBMIT_SMS + receiverAddress + NO_SPECIFIC_PROTOCOL + STANDARD_GSM_ALPHABET
                + userData;
    }

    private String encodeAddress(String addr) {
        addr = addr.replace(" ", "");
        String addressChanged = swapNumberDigits(addr);
        return toHexadecimal(addressChanged.length() / 2) + "81" + addressChanged;
    }

    private String swapNumberDigits(String num) {
        StringBuilder swappedNum = new StringBuilder();
        for (int index = 0; index < num.length(); index += 2) {
            if (index + 1 < num.length()) {
                swappedNum.append(num.charAt(index + 1)).append(num.charAt(index));
            } else {
                swappedNum.append(num.charAt(index)).append("F");
            }
        }
        return swappedNum.toString();
    }

    private String encodeMsg(String msg) {
        StringBuilder encodedMsg = new StringBuilder();
        for (char character : msg.toCharArray()) {
            encodedMsg.append(toHexadecimal(character));
        }
        return toHexadecimal(msg.length()) + encodedMsg;
    }

    public String getContent() {
        return content;
    }
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
        pdu = pduEncode(content, recipient);
        System.out.println(pdu + " to jest pdu");
    }

    public String getPdu() {
        return pdu;
    }
}

