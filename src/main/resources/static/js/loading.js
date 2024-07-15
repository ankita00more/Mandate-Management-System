$(document).ready(function() {
    // Append a loading animation element to the body
    $('body').append('<div class="loading-animation" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(255,255,255,0.8); z-index:1000; text-align:center;"><div style="position:absolute; top:50%; left:50%; transform:translate(-50%, -50%);"><img style="height:50px;width:50px;" src="images/loading.gif" alt="Loading..."/></div></div>');

    // Function to show the loading animation
    function showLoadingAnimation() {
        $('.loading-animation').show();
    }

    // Function to hide the loading animation
    function hideLoadingAnimation() {
        $('.loading-animation').hide();
    }

    // Show loading animation on page load start
    $(window).on('beforeunload', function() {
        showLoadingAnimation();
    });

    // Hide loading animation once the page is fully loaded
    $(window).on('load', function() {
        hideLoadingAnimation();
    });
	
    // Show loading animation when navigating via internal links
    $('a').on('click', function(e) {
		if ($("a").attr('class') === 'paginate_button ') {
               
                    hideLoadingAnimation();
              
            }
        if (this.href && this.href.indexOf('#') === -1) {
            showLoadingAnimation();

            // If the link opens in a new tab, hide the loading animation after 3 seconds
            if ($(this).attr('target') === '_blank') {
                setTimeout(function() {
                    hideLoadingAnimation();
                }, 500);
            }
            // If the link opens in a new tab, hide the loading animation after 3 seconds
            
        }
        
    });

    // Hide loading animation if navigation is prevented
    $(window).on('unload', function() {
        hideLoadingAnimation();
    });
});
