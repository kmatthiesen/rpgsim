
$(document).ready(function(){

  $('#kill-grain').on('click', function(){
    $.ajax({
      url:'/commodity/Grain/-60',
      method:'PUT'
    }).then(
          function(commodity){
            
              $('#view-commodities').append(JSON.stringify(commodity));


    });;
    });

  getCommodities();


});

function getCommodities() {
  $('#view-commodities').empty();
  $.ajax({
    url:'/commodity/view-all',
    method:'GET'
  }).then(
        function(commodities){
          for (var i=0; i<commodities.length; i++) {
            $('#view-commodities').append(JSON.stringify(commodities[i]));
          }

  });
}
