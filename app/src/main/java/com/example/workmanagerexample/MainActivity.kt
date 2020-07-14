package com.example.workmanagerexample

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Work Manager Instance
        val mWorkManager = WorkManager.getInstance(this)
        // Constraints
        val myConstraints: Constraints = Constraints.Builder()
            .setRequiresDeviceIdle(false)
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()
        // 2 minutes request
        val mRequest =
            OneTimeWorkRequest
                .Builder(AlertWorker::class.java)
                .setConstraints(myConstraints)
                .setInitialDelay(2, TimeUnit.MINUTES)
                .build()
        // 1 minute request
        val mRequest2 =
            OneTimeWorkRequest
                .Builder(AlertWorker::class.java)
                .setConstraints(myConstraints)
                .setInitialDelay(1, TimeUnit.MINUTES)
                .build()
        // 10 seconds request
        val mRequest3 =
            OneTimeWorkRequest
                .Builder(AlertWorker::class.java)
                .setConstraints(myConstraints)
                .setInitialDelay(10, TimeUnit.SECONDS)
                .build()
        // button 1 click Listener
        button.setOnClickListener {
            mWorkManager.enqueue(mRequest)
        }
        // button 2 click Listener
        button2.setOnClickListener {
            mWorkManager.enqueue(mRequest2)
        }
        // button 3 click Listener
        button3.setOnClickListener {
            mWorkManager.enqueue(mRequest3)
        }
        // work info for request 1
        mWorkManager.getWorkInfoByIdLiveData(mRequest.id)
            .observe(this, Observer<WorkInfo?> { workInfo ->
                if (workInfo != null) {
                    val state = workInfo.state
                    textView.text= "Work $state"
                }
            })
        // work info for request 2
        mWorkManager.getWorkInfoByIdLiveData(mRequest2.id)
            .observe(this, Observer<WorkInfo?> { workInfo ->
                if (workInfo != null) {
                    val state = workInfo.state
                    textView.text= "Work $state"
                }
            })
        // work info for request 3
        mWorkManager.getWorkInfoByIdLiveData(mRequest3.id)
            .observe(this, Observer<WorkInfo?> { workInfo ->
                if (workInfo != null) {
                    val state = workInfo.state
                    textView.text= "Work $state"
                }
            })
    }
}
