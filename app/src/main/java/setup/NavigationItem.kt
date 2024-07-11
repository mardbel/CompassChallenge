package setup

import androidx.navigation.NavType
import androidx.navigation.navArgument

enum class Feature(val route: String) {
    SPLASH("splash"),
    LOGIN("login"),
    FORGOT_PASSWORD("forgot_password"),
    WELCOME("welcome"),
    MAIN("main"),
    HOME("home"),
    SCHEDULE("schedule"),
    BOOKING("booking"),
    BUY("buy"),
    ACCOUNT("account"),
    ACCOUNT_PLANS("account_plans"),
    ACCOUNT_PLAN_DETAIL("account_class_detail"),
    STUDIOS("studios"),
    ANNOUNCEMENT_DETAIL("announcement_detail"),
    ANNOUNCEMENTS("announcements"),
    YOUR_PROFILE("your_profile"),
    YOUR_WALLET("your_wallet"),
    GIFT_CARDS("gift_cards"),
    PAYMENT_METHODS("payments_methods"),
    RESET_PASSWORD("reset_password"),
    TYC("terms_and_conditions"),
    COMMUNICATION("communication"),
    PLAN_LIST("plan_list"),
    PLAN_DETAIL("plan_detail"),
    CLASS_DETAIL("class_detail"),
    TRANSACTIONS("transactions"),
    BOOKING_DETAIL("booking_detail"),
    ADD_GUEST("add_guest"),
    THANK_YOU("thank_you"),
    NO_BUSINESS_SCREEN("no_business_screen"),
    WEB_VIEW("web_view"),
    DOWN_SYSTEM("down_system"),
    FORCE_UPDATE("force_update"),
    CONTACT_INFO("contact_info"),
    APPOINTMENTS_SELECT_STAFF("appointments_select_staff"),
    DELETE_ACCOUNT("delete_account"),
    SIGN_IN_OPTIONS("sign_in_options"),
    CREATE_ACCOUNT("create_account"),
    APPT_FRANCHISE_SELECT_CATEGORY("appt_franchise_select_category")
}


sealed class NavCommand(
    internal val feature: Feature,
    internal val subRoute: String = "home",
    private val navArgs: List<NavArg> = emptyList()
) {
    class ContentType(feature: Feature) : NavCommand(feature)

    class ContentTypeDetail(feature: Feature) :
        NavCommand(feature, "detail", listOf(NavArg.ItemId)) {
        fun createRoute(itemId: Int) = "${feature.route}/$subRoute/$itemId"
    }

    class ContentTypeSubRoute(feature: Feature, subRoute: String) :
        NavCommand(feature, subRoute) {
        fun createRoute() = "${feature.route}/$subRoute"
    }

    val route = run {
        val argValues = navArgs.map { "{${it.key}}" }
        listOf(feature.route)
            .plus(subRoute)
            .plus(argValues)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }

}

enum class NavArg(val key: String, val navType: NavType<*>) {
    ItemId("itemId", NavType.IntType)
}