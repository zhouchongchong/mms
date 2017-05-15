(function ($) {

    
    $(".nav .menu-temp").click(function() {
        $(this).toggleClass("active");
        $(".nav-temp").toggleClass("reveal");
    });

    /**
        menu show or close
    */
    $(".menu-sign").click(function() {
        $(".menu-box").toggleClass("spread");
        $(this).toggleClass("blocks");
        $(this).toggleClass("cross"); //mark X hover no letter

        $(".box").toggleClass("vague");
        
    });



    
    $("#user-menu").mouseover(function() {
        $(".vip-box-top").fadeIn();
        $(".vip-box").fadeIn();
    });
    $("#user-menu").mouseleave(function() {
        $(".vip-box-top").fadeOut();
        $(".vip-box").fadeOut();
    });


        
})(jQuery);
