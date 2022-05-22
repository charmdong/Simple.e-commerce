    // 아이디 중복체크 여부
    var isChecked = false;
    var inputId = '';

    // 아이디 중복체크
    function checkIdValidation() {
        var checkBtn = $('#checkBtn');
        var userId = $('#id').val();
        inputId = userId;
        $.ajax({
            type: 'GET',
            url: '/api/members/' + userId,
            success: function(data) {
                const isValid = data.isValid;
                alert(data.message);
                if (isValid == false) {
                    $('#id').val("");
                }
                else isChecked = true;
            },
            contentType: 'application/json'
        });
    }

    // form 유효성 검증
    function validateForm() {
        // 1. 이름

        // 2. 아이디
        if (isChecked == false || inputId != $('#id').val()) {
            alert("아이디 중복체크가 되지 않았습니다.");
            return false;
        }
        // 3. 패스워드
        if ($('#password').val() != $('#confirmPassword').val()) {
            alert("패스워드가 동일하지 않습니다.");
            return false;
        }
        // 4. 주소
    }