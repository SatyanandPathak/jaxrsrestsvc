package com.satya.restful.messenger.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.satya.restful.messenger.database.DatabaseClass;
import com.satya.restful.messenger.models.Message;

public class MessageService {
	
	DatabaseClass bd = new DatabaseClass();
	public static Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Message> getMessages(){
		return new ArrayList<Message>(messages.values());
	}
	
	
	
	public List<Message> getMessagesForYear(int year){
		List<Message> messagesForyear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for(Message message: messages.values()){
			cal.setTime(message.getCreated());
			if (cal.get(Calendar.YEAR) == year){
				messagesForyear.add(message);
			}
		}
		
		
		return messagesForyear;
		
	}
	
	
	
	
	public List<Message> getMessagesPaginated(int start, int size){
		List<Message> paginatedMessages = new ArrayList<>(messages.values());
		
		if(start + size > paginatedMessages.size()) {
			return new ArrayList<>();
		}
		
		return paginatedMessages.subList(start, start + size);
	}
	
	public Message getMessage(long messageId){
		return messages.get(messageId);
	}
	
	public Message addMessage(Message message){
		message.setId(messages.size() + 1);
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}
	
	public Message updateMessage(Message message){
		if (message.getId() < 0){
			return null;
		}
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}
	
	
	public Message removeMessage(long messageId){
		
		return messages.remove(messageId);
	}
	
	
	
	

}
