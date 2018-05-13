package com.example.trova.pycomchallenge;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.trova.pycomchallenge.DataProvider.GitNetworkProvider;
import com.example.trova.pycomchallenge.DataProvider.GitNetworkProviderInterface;
import com.example.trova.pycomchallenge.Model.GitEntry;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.mock.Calls;


import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class RestApiTest {

    @Mock
    private GitNetworkProviderInterface mockRetrofitApiImpl;

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.trova.pycomchallenge", appContext.getPackageName());
    }

    @Test
    public void createdRightCall() throws IOException {

        // Arrange
        GitNetworkProvider networkProvider = new GitNetworkProvider();
        mockRetrofitApiImpl = Mockito.mock(GitNetworkProviderInterface.class);
        networkProvider.setApiService(mockRetrofitApiImpl);
        Call<List<GitEntry>> call = Mockito.mock(Call.class);
        Mockito.when(mockRetrofitApiImpl.getCommits("rails", "rails")).thenReturn(call);

        // Execute
        networkProvider.getDataFromGitHub();

        // Assert
        Mockito.verify(mockRetrofitApiImpl).getCommits("rails", "rails");
    }


    @Test
    public void dataShouldDeserializedProperly() throws IOException {

        //Arrange
        GitNetworkProvider networkProvider = new GitNetworkProvider();
        mockRetrofitApiImpl = Mockito.mock(GitNetworkProviderInterface.class);
        networkProvider.setApiService(mockRetrofitApiImpl);
        setupMock(mockRetrofitApiImpl);

        //Execute
        List<GitEntry> result = networkProvider.getDataFromGitHub();

        //Asserts
        assertTrue("Result list must be of size 1", result.size() == 1);
        assertEquals("the name of author must be yuuji.yaginuma", "yuuji.yaginuma", result.get(0).getAuthorName());
        assertEquals("6fac9bd599eeb6b9cacdf7841811223402c501bd", result.get(0).getCommitId());
        String expectedMessage = "Pass the error instance as the second parameter of block executed by `discard_on`\n\nI'm not sure what originally wanted to pass to the argument.\nHowever, as long as see the document added along with the commit, it seems just\nto be mistaken that trying to pass the error instance.\nhttps://github.com/rails/rails/pull/30622/files#diff-59beb0189c8c6bc862edf7fdb84ff5a7R64\n\nFixes #32853";
        assertEquals(expectedMessage,result.get(0).getCommitMessage());

    }


    private void setupMock(GitNetworkProviderInterface mockUploadService) throws IOException{
        String jsonString =
                "[\n" +
                        "  {\n" +
                        "    \"sha\": \"6fac9bd599eeb6b9cacdf7841811223402c501bd\",\n" +
                        "    \"commit\": {\n" +
                        "      \"author\": {\n" +
                        "        \"name\": \"yuuji.yaginuma\",\n" +
                        "        \"email\": \"yuuji.yaginuma@gmail.com\",\n" +
                        "        \"date\": \"2018-05-09T09:20:54Z\"\n" +
                        "      },\n" +
                        "      \"committer\": {\n" +
                        "        \"name\": \"Yuji Yaginuma\",\n" +
                        "        \"email\": \"yuuji.yaginuma@gmail.com\",\n" +
                        "        \"date\": \"2018-05-12T04:55:25Z\"\n" +
                        "      },\n" +
                        "      \"message\": \"Pass the error instance as the second parameter of block executed by `discard_on`\\n\\nI'm not sure what originally wanted to pass to the argument.\\nHowever, as long as see the document added along with the commit, it seems just\\nto be mistaken that trying to pass the error instance.\\nhttps://github.com/rails/rails/pull/30622/files#diff-59beb0189c8c6bc862edf7fdb84ff5a7R64\\n\\nFixes #32853\",\n" +
                        "      \"tree\": {\n" +
                        "        \"sha\": \"0a743c8463000daa620bbc95b8e98f0005f2ece0\",\n" +
                        "        \"url\": \"https://api.github.com/repos/rails/rails/git/trees/0a743c8463000daa620bbc95b8e98f0005f2ece0\"\n" +
                        "      },\n" +
                        "      \"url\": \"https://api.github.com/repos/rails/rails/git/commits/6fac9bd599eeb6b9cacdf7841811223402c501bd\",\n" +
                        "      \"comment_count\": 0,\n" +
                        "      \"verification\": {\n" +
                        "        \"verified\": false,\n" +
                        "        \"reason\": \"unsigned\",\n" +
                        "        \"signature\": null,\n" +
                        "        \"payload\": null\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "]";


        List<GitEntry>  response =  GitNetworkProvider.mapper.readValue(jsonString, new TypeReference<List<GitEntry>>() {});
        Call call = Calls.response(response);
        Mockito
                .doReturn(call)
                .when(mockUploadService)
                .getCommits("rails","rails");

    }
}
