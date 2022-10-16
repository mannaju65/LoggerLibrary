package util;

import java.text.SimpleDateFormat;

import entity.MessageEntity;

public class BasicDateTimeMessageFormattor implements MessageFormattor {


    @Override
    public String formatMessage(MessageEntity messageEntity,String datePattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
        StringBuilder builder = new StringBuilder();
        builder.append(messageEntity.getNamespace()).append("  ");
        builder.append(messageEntity.getLogLevel().name()).append("  ");
        builder.append(messageEntity.getThreadId()).append("  ");
        builder.append("  [").append(dateFormat.format(messageEntity.getDate())).append("]  ");
        builder.append(messageEntity.getMessage()).append("\n");
        return builder.toString();
    }
    
}
