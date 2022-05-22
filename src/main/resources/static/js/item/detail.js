
    function deleteItem() {
        var result = confirm("상품 정보를 삭제하시겠습니까?");
        if(result == true) {
            document.querySelector('#deleteItem').click();
        }
        else return false;
    }