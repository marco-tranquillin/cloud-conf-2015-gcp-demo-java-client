/**
 * Copyright (C) 2015 marco.tranquillin@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package revevol.eu.cloudConf2015.gcpDemo.pubsub.client;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.pubsub.Pubsub;
import com.google.api.services.pubsub.PubsubScopes;
import com.google.api.services.pubsub.model.PublishRequest;
import com.google.api.services.pubsub.model.PublishResponse;
import com.google.api.services.pubsub.model.PubsubMessage;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

public class PubSubPublisher 
{
    public static void main( String[] args )
    {
        //read the number of messages that have to be published
    	String instanceName=args[0];
    	String topic=args[1];
    	String serviceAccountKeyPath=args[2];
    	int numberOfMessages=Integer.parseInt(args[3]);
    	
    	//generate the client
    	Pubsub pubsub=getClient(serviceAccountKeyPath);
    	if(pubsub==null){
    		System.out.println("An error occured: exit");
    		return;
    	}
    	
    	//publish all messages
    	for(int i=0;i<numberOfMessages;i++){
	    	//publish a single message
	    	Message msg=new Message();
	    		msg.setSender(instanceName);
	    		msg.setValue(getRandomInt(1,1000));
	    		msg.setTimestamp(new java.util.Date().getTime());
	    	List<String> messageIDs=publishMessage(pubsub,topic,new Gson().toJson(msg));
	    	if(messageIDs==null){
	    		System.out.println("An error occured during message publishing.");
	    	}
	    	else{
	    		//System.out.println("Message published correctly. Id: " + messageIDs.get(0));
	    	}
    	}
    }
    
    /**
     * Generate a random integer in a defined range
     * @param min min value of the random number
     * @param max max value of the random number
     * @return the generated random number
     */
    private static int getRandomInt(int min, int max){
    	Random rnd=new Random();
    	int randomNum = rnd.nextInt((max - min) + 1) + min;
    	return randomNum;
    }
    
    /**
     * Generate a PubSub client needed to communicate with Google Cloud PubSub
     * @param serviceAccountKeyPath the absolute path where is positioned the Service Account Key
     * @return PubSub client
     */
    private static Pubsub getClient(String serviceAccountKeyPath){
    	Pubsub pubsub=null;
    	
    	try{
	    	// define HTTP_TRANSPORT and JSON_FACTORY
			final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
			final JsonFactory JSON_FACTORY = new JacksonFactory();
	
			// load SERVICE ACCOUNT private key
			InputStream privateKeyStream = null;
			privateKeyStream = new FileInputStream(serviceAccountKeyPath);
			KeyStore ks = KeyStore.getInstance("PKCS12");
			ks.load(privateKeyStream, "notasecret".toCharArray());
			PrivateKey myOwnKey = (PrivateKey) ks.getKey("privatekey", "notasecret".toCharArray());
	
			// create OAuth 2.0 credentials based on PrivateKey
			GoogleCredential credential = new GoogleCredential.Builder()
				.setTransport(HTTP_TRANSPORT)
				.setJsonFactory(JSON_FACTORY)
				.setServiceAccountId(Constants.SERVICE_ACCOUNT_ID)
				.setServiceAccountScopes(Arrays.asList(PubsubScopes.PUBSUB,PubsubScopes.CLOUD_PLATFORM))
				.setServiceAccountPrivateKey(myOwnKey)
				.build();
	     	
			//create pub/sub client
	        pubsub = new Pubsub.Builder(HTTP_TRANSPORT, JSON_FACTORY, null)
			        .setApplicationName(Constants.APPLICATION_NAME)
			      	.setHttpRequestInitializer(credential)
			      	.build();	
	    	}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    	}
        
        return pubsub;
    }
    
    /**
     * Publish a message on the Google Cloud PubSub
     * @param client the PubSub client
     * @param topic the topic where you want to publish your message
     * @param message the message that you want to publish
     * @return
     */
    private static List<String> publishMessage(Pubsub client,String topic, String message){
    	PublishResponse publishResponse=null;
    	try{
	    	PubsubMessage pubsubMessage = new PubsubMessage();
	        pubsubMessage.encodeData(message.getBytes("UTF-8"));
	        List<PubsubMessage> messages = ImmutableList.of(pubsubMessage);
	        PublishRequest publishRequest = new PublishRequest().setMessages(messages);
	         publishResponse = client.projects().topics()
	                .publish("projects/"+Constants.PROJECT_ID+"/topics/"+topic, publishRequest)
	                .execute();
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(publishResponse!=null)
    		return publishResponse.getMessageIds();
    	else
    		return null;
    }
}
