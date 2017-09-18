package hk.ust.cse.comp107x.all_sign_up;

/**
 * Created by Dodo on 15/09/2017.
 */

public class ChatModel {

        String body;

        @Override
        public String toString() {
            return "ChatModel{" +
                    ", body='" + body + '\'' +
                    '}';
        }


        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public ChatModel(String body) {
            this.body = body;
        }

    }
