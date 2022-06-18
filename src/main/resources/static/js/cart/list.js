
    function cancel(cartId) {
        if(!confirm("장바구니에서 삭제하시겠습니까?")) return;

        $.ajax({
            type: "DELETE",
            url: "/api/cart?cartId=" + cartId,
            dataType: "json",
            success: function(response) {
                if(response.data == true) {
                    alert(response.message);
                    window.location.href = '/carts';
                }
            }
        });
    }