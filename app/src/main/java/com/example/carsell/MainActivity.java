package com.example.carsell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.carsell.Adapters.CarsRecycleAdapter;
import com.example.carsell.model.CarsList;
import com.example.carsell.retrofitUtils.APIClient;
import com.example.carsell.retrofitUtils.APIInterface;

public class MainActivity extends AppCompatActivity {

    private EndlessRecyclerOnScrollListener mScrollListener = null;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;
    APIInterface apiInterface;
    CarsRecycleAdapter carsRecycleAdapter;
    RecyclerView carsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        carsRecyclerView = (RecyclerView) findViewById(R.id.board_topic_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        carsRecyclerView.setLayoutManager(linearLayoutManager);

        // enable pull up for endless loading
        mScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int current_page) {
                loadViewData(current_page);
            }
        };
        carsRecyclerView.addOnScrollListener(mScrollListener);

        // enable pull down to refresh
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // do something
                carsRecycleAdapter = null;
                loadViewData(1);
            }
        });

        loadViewData(1);
    }
    public void loadViewData(int pageNumber)
    {
        mScrollListener.setLoading(true);
        Call<CarsList> call = apiInterface.getCarsList(""+pageNumber);
        call.enqueue(new Callback<CarsList>() {
            @Override
            public void onResponse(Call<CarsList> call, Response<CarsList> response) {


                Log.d("TAG",response.code()+"");

                String displayResponse = "";

                CarsList carsList = response.body();

                if(carsRecycleAdapter == null){
                    carsRecycleAdapter = new CarsRecycleAdapter(carsList);
                    carsRecyclerView.setAdapter(carsRecycleAdapter);
                    if (mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                        mSwipeRefreshLayout.setRefreshing(false);  // This hides the spinner
                    }
                }else{
                    carsRecycleAdapter.addData(carsList);
                }
                mScrollListener.setLoading(false);
            }

            @Override
            public void onFailure(Call<CarsList> call, Throwable t) {
                // Failed To Load Car Data
                Toast.makeText(MainActivity.this, "Failed to load the data, please check your connection",Toast.LENGTH_LONG);
                call.cancel();
            }
        });
    }
}
