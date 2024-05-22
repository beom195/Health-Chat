// 모달 요소와 모달을 여닫는 버튼 요소를 선택
let modal = document.getElementById("myModal");
let btn = document.getElementById("openModalBtn");
let closeBtn = document.getElementsByClassName("close-button")[0];

// 버튼 클릭 시 모달 열기
btn.onclick = function() {
    modal.style.display = "block";
}

// 닫기 버튼 클릭 시 모달 닫기
closeBtn.onclick = function() {
    modal.style.display = "none";
}

// 모달 바깥 영역 클릭 시 모달 닫기
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}