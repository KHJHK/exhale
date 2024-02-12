import axios from "axios";



//글 목록 조회 ~
// const boardList = async (board_id, page) => {
//     try {
//     const response = await axios.get(`http://i10b208.p.ssafy.io/api/boards/${board_id}?page=${page}`)
//     return response.data.response
//     } catch (error) {
//         console.error('글 목록을 가져오지 못했어요.', error)
//     }
// }
// 
const boardList = async (page, board_id) => {
    console.log('board.js 내부', board_id)
    return await axios.get(`http://i10b208.p.ssafy.io/api/boards/${board_id}?page=${page}`)
}

//게시글 검색 x
const boardSearch = async (board_id, searchType, searchContent, page) => {
    try {
    const response = await axios.post('http://i10b208.p.ssafy.io/api/boards/search', {
        board_id : board_id,
        searchType : searchType, // title, content, author
        searchContent : searchContent,
        page : page // int 
    }) 
    } catch (error) {
        console.error('검색을 완료하지 못했습니다.', error)
    }}

//글 상세 정보 조회 x
const boardDetail = async (article_id) => {
    
    return await axios.get(`http://i10b208.p.ssafy.io/api/articles/${article_id}`, {
    })  };

//게시글 생성 O
const articleCreate = async (title, content, thumbnail, board_id, accessToken) => {
    console.log('js',accessToken)
    try {
    const response = await axios.post('http://i10b208.p.ssafy.io/api/articles', {
        title: title,
        content: content,
        thumbnail: null,
        board_id: board_id
    }, {
        headers: {
            'Authorization': `${accessToken}`
        }
    });    
         if (response.status === 200) {
        alert('게시글 등록 완료!')
    }
    } catch (error) {
        console.error('게시글 등록에 실패하였습니다.', error)
        alert('로그인 후 이용해주세요.') // 원래는 status분기를 갈라서 다른 에러메시지를 출력해야함.
    }}

//이미지 저장 x
const saveImg = async (file) => {
 try {
    const response = await axios.post('http://i10b208.p.ssafy.io/api/articles/image/{article_id}', {
 }, { 
    headers : {
        "Content-Type": "multipart/form-data"
    }
    })
 } catch (error) {
    console.error('이미지를 저장할 수 없어요', error)
 }}

 
export {
  
    boardList,
    boardSearch,
    boardDetail,
    articleCreate,
    saveImg
};