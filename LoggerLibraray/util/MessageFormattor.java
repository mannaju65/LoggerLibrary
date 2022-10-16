package util;

import entity.MessageEntity;

public interface MessageFormattor {

    String formatMessage(MessageEntity messageEntity, String datePattern);
}
