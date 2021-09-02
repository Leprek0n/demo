$('document').ready(function() {
    $('.table .btn').on('click',function(event) {

        event.preventDefault();
        var href = $(this).attr('href');
        $.get(href, function(user, status){
            $('#idEdit').val(user.id);
            $('#nameEdit').val(user.username);
            $('#emailEdit').val(user.email);
            $('#passwordEdit').val(user.password);

        });
        $('#exampleModal').modal();

    });

});