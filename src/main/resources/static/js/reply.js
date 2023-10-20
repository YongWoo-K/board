/*#############모듈화################*/
let replyService = (function(){
    /*댓글 목록 함수*/
    function getList(callback){
        $.ajax({
            url: `/replies/list/${boardId}/${replyPage}/${rowCount}?type=${replyType}&keyword=${replyKeyword}`,
            dataType: "json",
            success: function(replyDTO){
                if(callback){
                    callback(replyDTO);
                }
            }
        });
    }

    /*댓글 등록 함수*/
    function write(callback){
        $.ajax({
            url:"/replies/write",
            type: "post",
            data: JSON.stringify({replyContent : $("textarea[name='replyContent']").val(), replyWriter : $("input[name='replyWriter']").val(), boardId : boardId}),
            contentType: "application/json; charset=utf-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    /*댓글 수정 함수*/
    function modify(callback){
        $.ajax({
            url: "/replies/modify",
            type: "post",
            data: JSON.stringify({replyContent : $("textarea.reply-content-modify").val(), replyId : $("textarea.reply-content-modify").closest("li").data("reply-id")}),
            contentType: "application/json; charset=utf-8",
            success: function(){
                if(callback){
                    callback();
                }
            }
        });
    }

    /*댓글 삭제 함수*/
    function remove(replyId, callback){
        $.ajax({
            url: `/replies/${replyId}`,
            type: "delete",
            success: function(){
                if(callback){
                    callback(replyId);
                }
            }
        });
    }
    return {getList: getList, write: write, modify: modify, remove: remove};
})();

/*댓글 목록 출력*/
replyService.getList(showList);


/*#############Event################*/
let check = false;

/*검색 키워드 입력 창의 키다운 이벤트*/
/* => 엔터 입력시 검색 결과가 나오도록 */
$("input[name='keyword']").on("keydown", function(e){
    if(e.keyCode == 13){
        e.preventDefault();
        replyPage = 1;
        $("a.search").trigger("click");
    }
})

/*검색 버튼의 클릭 이벤트*/
$("a.search").on("click",function(){
    replyType = $("select[name='type']").val();
    replyKeyword = $("input[name='keyword']").val();

    if(!replyType || !replyKeyword){
        return;
    }

    $("ul.replies").html("");
    replyPage = 1;
    replyService.getList(showList);
});

/*더보기 버튼의 클릭 이벤트*/
$("a.more-replies").on("click", function(){
    replyPage++;
    replyService.getList(showList);
});

/*삭제 버튼의 클릭 이벤트*/
$("ul.replies").on("click", "a.remove", function(){
    let replyId =$(this).closest("li").data("reply-id");
    replyService.remove(replyId, deleteReply);
});

/*수정 완료 버튼 클릭 이벤트*/
$("ul.replies").on("click", "a.modify", function(){
    check = false;
    replyService.modify(update);
});

/*수정 버튼 클릭 이벤트*/
$("ul.replies").on("click", "a.modify-ready", function(){
    if(check){return;}
    let i =$("a.modify-ready").index(this);
    $(this).hide();
    $("a.remove").eq(i).hide();
    $(this).parent("div").append(`<a class="modify" style="cursor:pointer; margin-right:5px;">수정 완료</a>`);
    $(this).parent("div").append(`<a class="modify-cancel" style="cursor:pointer">취소</a>`);
    $("p.reply-content").eq(i).hide();
    $("p.reply-content").eq(i).closest("div").append(`<textarea name="replyContent" class="reply-content-modify" style="resize:none; width:100%;" cols="120">${$("p.reply-content").eq(i).text()}</textarea>`);
    check = true;
})

/*수정 취소 버튼 클릭 이벤트*/
$("ul.replies").on("click", "a.modify-cancel", function(){
    check =false;
    const $p = $("textarea.reply-content-modify").closest("div").find("p.reply-content");
    $("a").remove("a.modify");
    $("a").remove("a.modify-cancel");
    $("textarea").remove("textarea.reply-content-modify");
    $p.show();
    $p.closest("li").find("a.modify-ready").show();
    $p.closest("li").find("a.remove").show();
});

/*댓글 등록 버튼 클릭 이벤트*/
$("a.register").on("click", function(){
    $("div.register-form").show();
    $(this).hide();
});

/*취소 버튼 클릭 이벤트*/
$("a.cancel").on("click", function(){
    $("div.register-form").hide();
    $("a.register").show();
    $("input[name='replyWriter']").val("");
    $("textarea[name='replyContent']").val("");
});

/*등록 버튼 클릭 이벤트*/
$("a.finish").on("click", function(){
    replyService.write(register);
});

/*#############DOM################*/
/*댓글 삭제 후 callback함수*/
let count = 0;
function deleteReply(replyId){
    $("li").remove(`li[data-reply-id=${replyId}]`);
    count++;
    if(count == 10 * replyPage){
        location.reload();
    }
}

/*댓글 수정 후 callback함수*/
function update(){
    const $p = $("textarea.reply-content-modify").closest("div").find("p.reply-content");
    $p.text($("textarea.reply-content-modify").val());
    $("a").remove("a.modify");
    $("a").remove("a.modify-cancel");
    $("textarea").remove("textarea.reply-content-modify");
    $p.show();
    $p.closest("li").find("a.modify-ready").show();
    $p.closest("li").find("a.remove").show();
}

/*댓글 등록 후 callback함수*/
function register(){
    replyPage = 1;
    $("ul.replies").html("");
    $("a.cancel").trigger("click");
    replyService.getList(showList);
}

/*목록 출력 함수*/
function showList(replyDTO){
    let replies = replyDTO.replies;
    let countOfNextPage = replyDTO.countOfNextPage;
    let text = "";

    if(replies.length == 0 && replyPage == 1){
        text = `<p>댓글이 없습니다.</p>`;
        $("a.more-replies").hide();
    }

    if(countOfNextPage == 0){
        $("a.more-replies").hide();
    } else if(countOfNextPage != 0){
        $("a.more-replies").show();
    }

    replies.forEach((reply, i) => {
        text += `
            <li style="display: block" data-reply-id="${reply.replyId}">
                <div style="display: flex; justify-content: space-between;">
                    <strong style="display: block">${reply.replyWriter}</strong>
                    
                    <div id="${i}">
                        <a class="modify-ready" style="cursor: pointer; margin-right: 5px;">수정</a>
                        <a class="remove" style="cursor: pointer">삭제</a>
                    </div>
                </div>
                
                <div style="display: flex; justify-content: space-between;">
                    <div>
                        <p class="reply-content">${reply.replyContent}</p>
                    </div>
                    <p style="text-align: right">
                        <strong class="date">
                            ${elapsedTime(reply.replyRegisterDate)}
                        </strong>
                    </p>
                </div>
                
                <div class="line"></div>
            </li>
        `;
    });
    $("ul.replies").append(text);
}

/*각 시간 단위를 초 단위로 계산 해주는 함수*/
function elapsedTime(date){
    const start = new Date(date);
    const end = new Date();
    const gap = (end - start) / 1000;
    const times = [
        {name : "년", time : 60 * 60 * 24 * 365},
        {name : "개월", time : 60 * 60 * 24 * 30},
        {name : "일", time : 60 * 60 * 24},
        {name : "시간", time : 60 * 60},
        {name : "분", time : 60}
    ]

    for(const time of times){
        const gapTime = Math.floor(gap / time.time);
        if(gapTime > 0){
            return `${gapTime}${time.name} 전`;
        }
    }

    return "방금 전";
}
