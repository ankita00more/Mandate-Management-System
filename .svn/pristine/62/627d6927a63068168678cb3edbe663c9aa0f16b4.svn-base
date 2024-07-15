jQuery(document).ready(function($) {
	$('body').append('<div class="loading-animation" style="display:none; position:fixed; top:0; left:0; width:100%; height:100%; background:rgba(255,255,255,0.8); z-index:1000; text-align:center;"><div style="position:absolute; top:50%; left:50%; transform:translate(-50%, -50%);"><img style="height:50px;width:50px;" src="images/loading.gif" alt="Loading..."/></div></div>');
    var table = $('.example').DataTable({
      "ordering": true,
      "lengthMenu": [5, 10, 25, 50, 100],
      dom: 'lBfrtip',
      buttons: [
        {
          text: 'Export All',
          extend: 'excelHtml5',
          customize: function(xlsx) {
            var sheet = xlsx.xl.worksheets['sheet1.xml'];
          }
        },
        'pdfHtml5'
      ]
    });
	
    $('.example').on('page.dt', function() {
      $('html, body').animate({
        scrollTop: $('.example').offset().top
      }, 'slow');
    });
  });
  
  /* jQuery(document).ready(function($) {
            function initializeDataTable(scrollX) {
                return $('.example').DataTable({
                    "ordering": true,
                    "scrollX": scrollX,
                    "lengthMenu": [5, 10, 25, 50, 100],
                    dom: 'lBfrtip',
                    buttons: [
                        {
                            text: 'Export All',
                            extend: 'excelHtml5',
                            customize: function(xlsx) {
                                var sheet = xlsx.xl.worksheets['sheet1.xml'];
                                // Customize your excel sheet here if needed
                            }
                        },
                        'pdfHtml5'
                    ]
                });
            }

            var table = initializeDataTable(true);

            function adjustScrollX() {
                var tableWidth = table.table().node().offsetWidth;
                var containerWidth = $('.container').width();

                if (tableWidth <= containerWidth) {
                    table.destroy();
                    table = initializeDataTable(false);
                } else {
                    table.destroy();
                    table = initializeDataTable(true);
                }
            }

            adjustScrollX();

            $(window).on('resize', function() {
                adjustScrollX();
            });

            $('.example').on('page.dt', function() {
                $('html, body').animate({
                    scrollTop: $('.example').offset().top
                }, 'slow');
            });
        });*/