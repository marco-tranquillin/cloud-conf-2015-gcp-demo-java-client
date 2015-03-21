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

public class Constants {
	/**
	 * OAuth 2.0 Keys
	 */
	public static final String		SERVICE_ACCOUNT_KEY_PATH		= "ServiceAccountPK.p12";
	public static final String		SERVICE_ACCOUNT_ID				= "YOUR_SERVICE_ACCOUNT_ID";
	public static final String		SERVICE_ACCOUNT_EMAIL			= "YOUR_SERVICE_ACCOUNT_EMAIL";
	public static final String[]	OAUTH_SCOPES					= { "https://www.googleapis.com/auth/compute" };
	public static final String		OAUTH_USER						= "YOUR_USER";

	/**
	 * Google Compute Engine
	 */
	public static final String		APPLICATION_NAME				= "YOUR_APP_NAME";
	public static final String		PROJECT_ID						= "YOUR_PROJECT_ID";
	public static final String		GCE_MACHINE_DEFAULT_ZONE		= "us-central1-a";
	public static final String		GCE_MACHINE_TYPE_FULL_URL		= "https://www.googleapis.com/compute/v1/projects/YOUR_PROJECT_ID/zones/xxxZONExxx/machineTypes/xxxMACHINE_TYPExxx";
	public static final String		GCE_MACHINE_TYPE_N1_HIGHCPU_2	= "n1-highcpu-2";
	public static final String		GCE_MACHINE_TYPE_F1_MICRO		= "f1-micro";
	public static final String		GCE_LINUX_BOOT_IMG				= "https://www.googleapis.com/compute/v1/projects/debian-cloud/global/images/debian-7-wheezy-v20150309";
	public static final String		GCE_NETWORK_TYPE				= "https://www.googleapis.com/compute/v1/projects/YOUR_PROJECT_ID/global/networks/default";
	public static final int			GCE_MAX_INSTANCES				= 1;
	public static final String		GCE_METADATA_STARTUP_KEY		= "startup-script-url";
	public static final String		GCE_METADATA_STARTUP_VALUE		= "http://storage.googleapis.com/YOUR_BUCKET_ID/cloud-conf-2015-startup-script-1.0.sh";
	public static final String		GCE_METADATA_MESSAGE_POST		= "message-post";
	public static final String		GCE_METADATA_MESSAGE_POST_VALUE	= "api/message/post";
	public static final String		GCE_METADATA_MESSAGE_POST_NUMBER		= "message-post-number";
	public static final String		GCE_METADATA_MESSAGE_POST_NUMBER_VALUE	= "100";
	public static final String		GCE_METADATA_INSTANCE_NAME		= "instance-name";
	public static final String		GCE_METADATA_INSTANCE_ZONE		= "instance-zone";
	public static final String		GCE_METADATA_INSTANCE_START_METHOD_NAME	= "instance-start";
	public static final String		GCE_METADATA_INSTANCE_START_METHOD_VALUE	= "api/gce/instance/start";
	public static final String		GCE_METADATA_INSTANCE_STOP_METHOD_NAME	= "instance-stop";
	public static final String		GCE_METADATA_INSTANCE_STOP_METHOD_VALUE	= "api/gce/instance/stop";
	public static final String		GCE_NETWORK_EMPHERAL			= "ONE_TO_ONE_NAT";
	public static final long		GCE_LIST_MAX_NUMBER				= 1;
	public static final String	    GCE_DEFAULT_INSTANCE_NAME		= "publisher";
	
	/**
	 * Cloud Pub&Sub
	 */
	public static final String	    CLOUD_PUB_SUB_WEBHOOK		= "api/pubsub/topic/push/handler";
	

	/**
	 * HTTP
	 */
	public static final String		PROTOCOL_S						= "https";
	public static final String		PROTOCOL						= "http";
	public static final String		HTTP_STATUS_200					= "200 OK";
	public static final String		HTTP_STATUS_204					= "204 No content";
	public static final String		HTTP_STATUS_501					= "501 Method not recognized";
	public static final String		HTTP_STATUS_401					= "401 Unauthorized";

}
