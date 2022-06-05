
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

    function validateCart() {
        let count = $('#count').val();

        if (count < 1) {
            alert("제품을 1개 이상 선택해주세요.");
            return false;
        }

        return true;
    }

    function isCartDuplicated() {

        if (!validateCart()) return;

        let itemId = $('#itemId').val();

        $.ajax({
            type: "GET",
            url: "/api/cart/" + itemId,
            dataType: "json",
            success: function(response) {
                if(response.data == false) {
                    addCart();
                }
                else {
                    alert(response.message);
                }
            }
        })
    }

    function addCart() {
        let param = {};
        param.itemId = $('#itemId').val();
        param.count = $('#count').val();

        $.ajax({
            type: "POST",
            url: "/api/cart",
            dataType: "json",
            data: param,
            success: function(response) {
                alert(response.message);
            }
        });
    }