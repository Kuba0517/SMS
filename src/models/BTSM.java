package models;

import events.MessageSender;
import events.MessageSenderBTS;
import events.Senders;

    public class BTSM extends NetworkDevice<models.BTSM> {
        private MessageSenderBTS messageSender;
        private final Object lock = new Object();
        private boolean startBTS;

        public BTSM() {
            super();
        }


        public void setStartBTS(boolean startBTS) {
            this.startBTS = startBTS;
        }

        public boolean isStartBTS(){
            return startBTS;
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    Message message;
                    synchronized (lock) {
                        while (messages.isEmpty()) {
                                lock.wait();
                        }
                        message = getMessagePeek();
                    }
                    if (message != null) {
                            Thread.sleep(3000);
                            messageSender.sendMessageBTS(this);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        @Override
        public void addMessage(Message message) {
            synchronized (lock) {
                messages.add(message);
                lock.notify();
            }
            fireViewUpdate();
        }
        @Override
        public void setMessageSender(Senders messageSender) {
            this.messageSender = (MessageSenderBTS) messageSender;
        }



}


