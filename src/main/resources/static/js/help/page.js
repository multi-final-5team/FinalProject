function getpaging(result){
    console.log(result.paging.startPage);
    $('#paginationDiv').html('');
    let pageHtml= '';
    // 이전 페이지 블록 화살표 처리-+
    if (result.paging.startPage != 1) {
        pageHtml += `
            <a class="page-link" href="#" onclick="getList(${result.paging.startPage - 1});" aria-label="Previous">
                <span aria-hidden="true">&lt;</span>
            </a>
        `;
    }

    // 페이지 번호 처리
    for (let i = result.paging.startPage ; i <= result.paging.endPage; i++) {
        if (i == result.paging.nowPage) {
            pageHtml += `<a class="page-link">${i}</a>
            `;
        } else {
            pageHtml += `<a class="page-link" href="#" onclick="getList(${i});">${i}</a>
            `;
        }
    }

    // 다음 페이지 블록 화살표 처리
    if (result.paging.endPage != result.paging.lastPage) {
        pageHtml += `
            <a class="page-link" href="#" onclick="getList(${result.paging.endPage+1 });" aria-label="Next">
                <span aria-hidden="true">&gt;</span>
            </a>
        `;
    }

    $('#paginationDiv').append(pageHtml);
    }
