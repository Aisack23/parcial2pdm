package com.isto.parcialdos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.isto.parcialdos.ui.navigation.NavGraph
import com.isto.parcialdos.ui.theme.CatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogTheme {
                NavGraph()
            }
        }
    }
}