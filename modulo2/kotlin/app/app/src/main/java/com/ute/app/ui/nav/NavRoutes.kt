package com.ute.app.ui.nav

sealed class NavRoute(val route: String, val label: String, val emoji: String) {
    data object Home : NavRoute("home", "Inicio", "🏠")
    data object Posts : NavRoute("posts", "Posts", "📝")
    data object Account : NavRoute("account", "Cuenta", "👤")

    // Rutas adicionales que no están en el bottom bar
    data object PostDetail : NavRoute("post-detail/{id}", "Detalle", "ℹ️") {
        fun build(id: Int) = "post-detail/$id"
    }
    data object Settings : NavRoute("settings", "Ajustes", "⚙️")
    data object Gallery : NavRoute("gallery", "Galería", "🖼️")
    data object Video : NavRoute("video", "Video", "🎬")

    companion object { val bottomTabs = listOf(Home, Posts, Account) }
}