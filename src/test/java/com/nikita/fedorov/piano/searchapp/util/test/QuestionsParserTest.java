package com.nikita.fedorov.piano.searchapp.util.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nikita.fedorov.piano.searchapp.bean.QuestionBean;
import com.nikita.fedorov.piano.searchapp.util.QuestionsParser;

import java.util.ArrayList;
import java.util.Date;

public class QuestionsParserTest {
	private final static String testData = "{\"items\":[{\"tags\":[\"java\"],\"owner\":{\"reputation\":133,\"user_id\":2303615,\"user_type\":\"registered\",\"profile_image\":\"https://www.gravatar.com/avatar/cbf09c4004880ef03dd8acb83a1f519e?s=128&d=identicon&r=PG\",\"display_name\":\"Jon A\",\"link\":\"https://stackoverflow.com/users/2303615/jon-a\"},\"is_answered\":true,\"view_count\":98592,\"accepted_answer_id\":16127946,\"answer_count\":7,\"score\":26,\"last_activity_date\":1505034905,\"creation_date\":1366517847,\"last_edit_date\":1505034905,\"question_id\":16127923,\"link\":\"https://stackoverflow.com/questions/16127923/checking-letter-case-upper-lower-within-a-string-in-java\",\"title\":\"Checking letter case (Upper/Lower) within a string in Java\"},{\"tags\":[\"java\",\"xml\",\"maven\",\"xsd\",\"jaxb\"],\"owner\":{\"reputation\":1,\"user_id\":5555246,\"user_type\":\"registered\",\"profile_image\":\"https://lh5.googleusercontent.com/--UsZrtX9U3U/AAAAAAAAAAI/AAAAAAAADIo/98hBVxn_3bI/photo.jpg?sz=128\",\"display_name\":\"moly\",\"link\":\"https://stackoverflow.com/users/5555246/moly\"},\"is_answered\":false,\"view_count\":25,\"answer_count\":1,\"score\":0,\"last_activity_date\":1505034828,\"creation_date\":1504862783,\"last_edit_date\":1505034828,\"question_id\":46113084,\"link\":\"https://stackoverflow.com/questions/46113084/xsd-to-java-with-jaxb-list-with-duplicated-indentation\",\"title\":\"XSD to Java with JAXB list with duplicated indentation\"}],\"has_more\":true,\"quota_max\":300,\"quota_remaining\":275}";

	@Test
	public void testParseQuestions() {
		ArrayList<QuestionBean> questions = QuestionsParser.ParseQuestions(testData);
		Assert.assertEquals(2, questions.size());
		
		QuestionBean q1 = questions.get(0);		
		Assert.assertEquals("Checking letter case (Upper/Lower) within a string in Java", q1.getTitle());
		Assert.assertEquals("Jon A", q1.getAuthor());
		Assert.assertEquals(new Date(1366517847L * 1000), q1.getCreationDate());
		Assert.assertEquals(true, q1.getIsAnswered());
		Assert.assertEquals("https://stackoverflow.com/questions/16127923/checking-letter-case-upper-lower-within-a-string-in-java", q1.getLink());
		
		q1 = questions.get(1);		
		Assert.assertEquals("XSD to Java with JAXB list with duplicated indentation", q1.getTitle());
		Assert.assertEquals("moly", q1.getAuthor());
		Assert.assertEquals(new Date(1504862783L * 1000), q1.getCreationDate());
		Assert.assertEquals(false, q1.getIsAnswered());
		Assert.assertEquals("https://stackoverflow.com/questions/46113084/xsd-to-java-with-jaxb-list-with-duplicated-indentation", q1.getLink());
	}

}
