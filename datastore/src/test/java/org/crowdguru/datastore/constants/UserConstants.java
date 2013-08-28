package org.crowdguru.datastore.constants;

import org.crowdguru.datastore.domain.User;

//http://www.babynames.com/features/widgets.php?&action=renamer
public interface UserConstants {
	String USER_1_FORENAME = "Caleb"; 
	String USER_1_SURNAME = "Ace";
	String USER_1_SHORTPROFILE = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem.";
	String USER_1_LOCATION = "London";
	String USER_1_PROVIDER_ID = "accusantium";
	String USER_1_PROVIDER_USERID = "doloremque";
	String USER_1_EMAIL = "calebace@gmail.com";
	String USER_1_PASSWORD = "calebace";
	User.Type USER_1_TYPE = User.Type.KEYCONTACT;
	
	String USER_2_FORENAME = "Nikkol"; 
	String USER_2_SURNAME = "Shiva";
	String USER_2_SHORTPROFILE = "Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet consectetur.";
	String USER_2_LOCATION = "Brighton";
	String USER_2_PROVIDER_ID = "dolores";
	String USER_2_PROVIDER_USERID = "magni";
	String USER_2_EMAIL = "nikkolshiva@hotmail.com";
	String USER_2_PASSWORD = "nikkolshiva";
	User.Type USER_2_TYPE = User.Type.GURU;
	
	String USER_3_FORENAME = "Ishik"; 
	String USER_3_SURNAME = "Addy";
	String USER_3_SHORTPROFILE = "Quis autem vel eum iure reprehenderit qui in ea voluptate";
	String USER_3_LOCATION = "Bristol";
	String USER_3_PROVIDER_ID = "sequi";
	String USER_3_PROVIDER_USERID = "nesciunt";
	String USER_3_EMAIL = "ishik_addy@yahoo.com";
	String USER_3_PASSWORD = "ishikaddy";
	User.Type USER_3_TYPE = User.Type.BOTH;
}
