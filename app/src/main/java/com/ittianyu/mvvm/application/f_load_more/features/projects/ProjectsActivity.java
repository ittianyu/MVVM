package com.ittianyu.mvvm.application.f_load_more.features.projects;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.ittianyu.mvvm.R;
import com.ittianyu.mvvm.application.f_load_more.common.bean.Lcee;
import com.ittianyu.mvvm.application.f_load_more.common.bean.ListStatus;
import com.ittianyu.mvvm.application.f_load_more.common.bean.Status;
import com.ittianyu.mvvm.application.f_load_more.common.bean.project.Projects;
import com.ittianyu.mvvm.application.f_load_more.common.repository.ProjectsRepository;
import com.ittianyu.mvvm.application.f_load_more.common.repository.local.db.DBHelper;

/**
 * Created by 86839 on 2017/10/4.
 */

public class ProjectsActivity extends AppCompatActivity {
    private static final String TAG = ProjectsActivity.class.getName();
    // vm
    private ProjectsViewModel projectsViewModel;

    // view
    private View vContent;
    private View vError;
    private View vLoading;
    private View vEmpty;

    private SwipeRefreshLayout srl;
    private RecyclerView rv;
    private ProjectsAdapter projectsAdapter;

    private Status status;
    private ListStatus listStatus = ListStatus.Content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.f_activity_projects);
        initView();
        initData();
        initEvent();
    }

    private void initView() {
        vContent = findViewById(R.id.v_content);
        vError = findViewById(R.id.v_error);
        vLoading = findViewById(R.id.v_loading);
        vEmpty = findViewById(R.id.v_empty);

        srl = (SwipeRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        projectsAdapter = new ProjectsAdapter();
        rv.setAdapter(projectsAdapter);
    }

    private void initData() {
        DBHelper.getInstance().init(this);
        ProjectsRepository.getInstance().init(this);

        projectsViewModel = ViewModelProviders.of(this).get(ProjectsViewModel.class);
        projectsViewModel.getProjects().observe(this, new Observer<Lcee<Projects>>() {
            @Override
            public void onChanged(@Nullable Lcee<Projects> data) {
                updateView(data);
            }
        });

        reload();
    }

    private void initEvent() {
        View.OnClickListener reloadClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload();
            }
        };
        vError.setOnClickListener(reloadClickListener);
        vEmpty.setOnClickListener(reloadClickListener);

        // refresh
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isLoading()) {
                    srl.setRefreshing(false);
                    return;
                }
                listStatus = ListStatus.Refreshing;
                reload();
            }
        });

        // load more
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public int mLastVisibleItemPosition;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    mLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
                }
                if (projectsAdapter == null)
                    return;

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && mLastVisibleItemPosition + 1 == projectsAdapter.getItemCount()) {

                    if (isLoading())
                        return;
                    listStatus = ListStatus.LoadingMore;
                    loadMore();
                }
            }
        });

    }


    private void reload() {
        projectsViewModel.reload();
    }

    private void loadMore() {
        projectsViewModel.loadMore(projectsAdapter.getItemCount());
    }

    private void updateView(Lcee<Projects> lcee) {
        status = lcee.status;
        switch (lcee.status) {
            case Content: {
                updateContentView(lcee.data);
                break;
            }
            case Empty: {
                updateEmptyView();
                break;
            }
            case Error: {
                updateErrorView();
                break;
            }
            case Loading: {
                updateLoadingView();
                break;
            }
        }
    }


    private void updateContentView(Projects projects) {
        switch (listStatus) {
            case LoadingMore: {
                projectsAdapter.addData(projects.getItems());
                // todo load more complete

                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                break;
            }
            default: {
                showContent();
                projectsAdapter.setData(projects.getItems());
                break;
            }
        }
    }

    private void updateEmptyView() {
        switch (listStatus) {
            case LoadingMore: {
                // todo no more data

                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                showEmpty();
                break;
            }
            default: {
                showEmpty();
                break;
            }
        }
    }

    private void updateErrorView() {
        switch (listStatus) {
            case LoadingMore: {
                // todo load more error

                break;
            }
            case Refreshing: {
                srl.setRefreshing(false);
                Toast.makeText(this, "Refresh failed", Toast.LENGTH_SHORT).show();
                break;
            }
            default: {
                showError();
                break;
            }
        }
    }

    private void updateLoadingView() {
        switch (listStatus) {
            case LoadingMore: {
                // todo show loading more view in list footer

                break;
            }
            case Refreshing: {

                break;
            }
            default: {
                showLoading();
                break;
            }
        }
    }

    private void showContent() {
        vContent.setVisibility(View.VISIBLE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showEmpty() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.VISIBLE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.GONE);
    }

    private void showError() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.VISIBLE);
        vLoading.setVisibility(View.GONE);
    }

    private void showLoading() {
        vContent.setVisibility(View.GONE);
        vEmpty.setVisibility(View.GONE);
        vError.setVisibility(View.GONE);
        vLoading.setVisibility(View.VISIBLE);
    }

    private boolean isLoading() {
        return status == Status.Loading;
    }
}
