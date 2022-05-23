
    function getTotalPrice() {
        const price = $('#price').val();
        const count = $('#count').val();

        $('#totalPrice').val(Number(price * count));
    }