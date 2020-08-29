package com.vladan.color_interview.view.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vladan.color_interview.R;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";
    OkHttpClient restClient;
    Request mRequest;
    JSONObject jsonObject;
    String jwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            ListFragment listFragment = new ListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, listFragment).commit();
        }
//        String uuid = UuidGenerator.generateUuid();
//        String identity = "user";
//        jwt = JWTUtils.createJWT("HS256","JWT", uuid,identity);
//        restClient = new OkHttpClient();
//
//        mRequest = new Request.Builder()
//                .method("GET", null)
//                .url("http://opn-interview-service.nn.r.appspot.com/list")
//                .addHeader("Authorization", "Bearer " + jwt)
//                .build();
//        restClient.newCall(mRequest).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.d(TAG,e.toString());
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                if (response.code()==200){
//
//                    String responseData = response.body().string();
//                    try {
//                         jsonObject = new JSONObject(responseData);
//                        Log.d(TAG,jsonObject.toString());
//                        // Do something here
//                    } catch (JSONException e) {
//
//}
//                }
//                Log.d(TAG,response.toString());
//
//                Log.d(TAG, response.message());
//            }
//        });

//        ApiService client = RetrofitService.create(getApplicationContext());
//        client.getIdList().enqueue(new Callback<ListIds>() {
//                                       @Override
//                                       public void onResponse(Call<ListIds> call, Response<ListIds> response) {
//                                             String result = response.body().toString();
//                                       }
//
//                                       @Override
//                                       public void onFailure(Call<ListIds> call, Throwable t) {
//                                           String error = t.getMessage();
//
//                                       }
//                                   }
//        );

    }
}