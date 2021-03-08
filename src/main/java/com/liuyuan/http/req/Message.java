package com.liuyuan.http.req;



import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class Message<T> implements Serializable {
    private Header header;
    private Body body;
    private boolean skipSent = false;

    public Message(WlhyMessageType messageType, String documentName, String ipcType, T t) {
        this.header = new Header(messageType, documentName, ipcType);
        this.body = new Body(t);
    }

    public Message(Class<T> tClass) {
        this.header = new Header();
        this.body = new Body();

        try {
            body.setEncryptedContent(tClass.newInstance());
        } catch (IllegalAccessException | InstantiationException var3) {
            var3.printStackTrace();
        }
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public boolean isSkipSent() {
        return skipSent;
    }

    public void setSkipSent(boolean skipSent) {
        this.skipSent = skipSent;
    }

    public String toString() {
        return "Message(header=" + this.getHeader() + ", body=" + this.getBody() + ", skipSent=" + this.isSkipSent() + ")";
    }

    public static class Header implements Serializable {
        private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
        private WlhyMessageType messageType;
        private String messageReferenceNumber;
        private String documentName;
        private String documentVersionNumber;
        private String ipcType;
        private String userName;
        private String token;
        private String senderCode;
        private String password;
        private String channel;
        private Integer debugFlag;
        private String key;
        private String uniqueKey;
        private String messageSendingDateTime;

        public Header(WlhyMessageType messageType, String documentName, String ipcType, String sender, String password, String channel, Integer debugFlag, String key, String uniqueKey) {
            this.messageType = messageType;
            this.messageReferenceNumber = UUID.randomUUID().toString().replace("-", "");
            this.documentName = documentName;
            this.documentVersionNumber = "2.0";
            this.messageSendingDateTime = DATE_FORMAT.format(Calendar.getInstance().getTime());
            this.ipcType = ipcType;
            this.senderCode = sender;
            this.password = password;
            this.channel = channel;
            this.debugFlag = debugFlag;
            this.key = key;
            this.uniqueKey = uniqueKey;
        }

        public Header(WlhyMessageType messageType, String documentName, String ipcType) {
            this.messageReferenceNumber = UUID.randomUUID().toString().replace("-", "");
//            this.messageType = messageType;
            this.documentName = documentName;
            this.documentVersionNumber = "2.0";
            this.messageSendingDateTime = DATE_FORMAT.format(Calendar.getInstance().getTime());
            this.ipcType = ipcType;
        }

        public Header() {
        }

        public WlhyMessageType getMessageType() {
            return this.messageType;
        }

        public String getMessageReferenceNumber() {
            return this.messageReferenceNumber;
        }

        public String getDocumentName() {
            return this.documentName;
        }

        public String getDocumentVersionNumber() {
            return this.documentVersionNumber;
        }

        public String getIpcType() {
            return this.ipcType;
        }

        public String getUserName() {
            return this.userName;
        }

        public String getToken() {
            return this.token;
        }

        public String getSenderCode() {
            return this.senderCode;
        }

        public String getPassword() {
            return this.password;
        }

        public String getChannel() {
            return this.channel;
        }

        public Integer getDebugFlag() {
            return this.debugFlag;
        }

        public String getKey() {
            return this.key;
        }

        public String getUniqueKey() {
            return this.uniqueKey;
        }

        public String getMessageSendingDateTime() {
            return this.messageSendingDateTime;
        }

        public void setMessageType(final WlhyMessageType messageType) {
            this.messageType = messageType;
        }

        public void setMessageReferenceNumber(final String messageReferenceNumber) {
            this.messageReferenceNumber = messageReferenceNumber;
        }

        public void setDocumentName(final String documentName) {
            this.documentName = documentName;
        }

        public void setDocumentVersionNumber(final String documentVersionNumber) {
            this.documentVersionNumber = documentVersionNumber;
        }

        public void setIpcType(final String ipcType) {
            this.ipcType = ipcType;
        }

        public void setUserName(final String userName) {
            this.userName = userName;
        }

        public void setToken(final String token) {
            this.token = token;
        }

        public void setSenderCode(final String senderCode) {
            this.senderCode = senderCode;
        }

        public void setPassword(final String password) {
            this.password = password;
        }

        public void setChannel(final String channel) {
            this.channel = channel;
        }

        public void setDebugFlag(final Integer debugFlag) {
            this.debugFlag = debugFlag;
        }

        public void setKey(final String key) {
            this.key = key;
        }

        public void setUniqueKey(final String uniqueKey) {
            this.uniqueKey = uniqueKey;
        }

        public void setMessageSendingDateTime(final String messageSendingDateTime) {
            this.messageSendingDateTime = messageSendingDateTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Header header = (Header) o;
            return messageType == header.messageType && Objects.equals(messageReferenceNumber, header.messageReferenceNumber) && Objects.equals(documentName, header.documentName) && Objects.equals(documentVersionNumber, header.documentVersionNumber) && Objects.equals(ipcType, header.ipcType) && Objects.equals(userName, header.userName) && Objects.equals(token, header.token) && Objects.equals(senderCode, header.senderCode) && Objects.equals(password, header.password) && Objects.equals(channel, header.channel) && Objects.equals(debugFlag, header.debugFlag) && Objects.equals(key, header.key) && Objects.equals(uniqueKey, header.uniqueKey) && Objects.equals(messageSendingDateTime, header.messageSendingDateTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(messageType, messageReferenceNumber, documentName, documentVersionNumber, ipcType, userName, token, senderCode, password, channel, debugFlag, key, uniqueKey, messageSendingDateTime);
        }

        public String toString() {
            return "Header(messageType=" + this.getMessageType() + ", messageReferenceNumber=" + this.getMessageReferenceNumber() + ", documentName=" + this.getDocumentName() + ", documentVersionNumber=" + this.getDocumentVersionNumber() + ", ipcType=" + this.getIpcType() + ", userName=" + this.getUserName() + ", token=" + this.getToken() + ", senderCode=" + this.getSenderCode() + ", password=" + this.getPassword() + ", channel=" + this.getChannel() + ", debugFlag=" + this.getDebugFlag() + ", key=" + this.getKey() + ", uniqueKey=" + this.getUniqueKey() + ", messageSendingDateTime=" + this.getMessageSendingDateTime() + ")";
        }
    }

    public static class Body<T> implements Serializable {
        private String encryptedCode;
        private T encryptedContent;

        public Body(T t) {
            this.encryptedContent = t;
        }

        public Body() {
        }

        public String getEncryptedCode() {
            return encryptedCode;
        }

        public void setEncryptedCode(String encryptedCode) {
            this.encryptedCode = encryptedCode;
        }

        public T getEncryptedContent() {
            return encryptedContent;
        }

        public void setEncryptedContent(T encryptedContent) {
            this.encryptedContent = encryptedContent;
        }
    }
}
