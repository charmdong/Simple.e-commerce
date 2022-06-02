
    function getTotalPrice() {
        const price = $('#price').val();
        const count = $('#count').val();

        $('#totalPrice').val(Number(price * count));
    }

    function orderItem() {
        let orderForm = {};

        orderForm.itemId = $('#itemId').val();
        orderForm.count = $('#count').val();

        $.ajax({
            type: "POST",
            url: "/api/order",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(orderForm),
            success: function(data) {
                alert(data.message);
                if(data.result == true) {
                    location.href = '/orders';
                }
            }
        });
    }

    function addCart() {
        let param = {};
        param.itemId = $('#itemId').val();
        param.count = $('#count').val();

        $.ajax({
            type: "POST",
            url: "/api/cart",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(param),
            success: function(response) {
                alert(response.message);
            }
        });
    }