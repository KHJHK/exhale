import axios from "axios";



//글 목록 조회 ~
// const boardList = async (board_id, page) => {
const accessToken = localStorage.getItem("JWT_token");
//카테고리 조회 요청
// const boardCategory = async () => {
//     try {
//     const response = await axios.get(`http://i10b208.p.ssafy.io/api/boards/${board_id}?page=${page}`)
//     return response.data.response
//     } catch (error) {
//         console.error('글 목록을 가져오지 못했어요.', error)
//     const response = await axios.get('http://i10b208.p.ssafy.io/api/boards')

//     console.log(response.data.response)

//     } catch (error){
//         console.error('분류가 안되네요 지금', error)
//     }
// }

// }

//글 목록 조회 ~
const boardList = async (board_id, page) => {
  try {
    const response = await axios.get(
      `http://i10b208.p.ssafy.io/api/boards/${board_id}?page=${page}`,
      {
        board_id: board_id,
        page: page,
      }
    );
    console.log(response.data);
  } catch (error) {
    console.error("글 목록을 가져오지 못했어요.", error);
  }
};

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
  try {
    const response = await axios.post(
      "http://i10b208.p.ssafy.io/api/boards/search",
      {
        board_id: board_id,
        searchType: searchType, // title, content, author
        searchContent: searchContent,
        page: page, // int
      }
    );
  } catch (error) {
    console.error("검색을 완료하지 못했습니다.", error);
  }


//글 상세 정보 조회 x
const boardDetail = async (article_id) => {
    
    return await axios.get(`http://i10b208.p.ssafy.io/api/articles/${article_id}`, {
    })  };
// const boardDetail = async () => {
//   try {
//     const response = await axios.get(
//       "http://i10b208.p.ssafy.io/api/articles/{article_id}",
//       {
//         headers: {
//           Authorization: `${accessToken}`,
//         },
//       }
//     );
//   } catch (error) {
//     console.log("게시물을 찾을 수 없습니다.", error);
//   }
// };

//게시글 생성 O
const articleCreate = async (title, content, thumbnail, board_id, accessToken) => {
    console.log('js',accessToken)
  try {
    const response = await axios.post(
      "http://i10b208.p.ssafy.io/api/articles",
      {
        title: title,
        content: content,
        thumbnail: null,
        board_id: board_id,
      },
      {
        headers: {
          Authorization: `${accessToken}`,
        },
      }
    );
    if (response.status === 200) {
      alert("게시글 등록 완료!");
    }
  } catch (error) {
    console.error("게시글 등록에 실패하였습니다.", error);
    alert("로그인 후 이용해주세요."); // 원래는 status분기를 갈라서 다른 에러메시지를 출력해야함.
  }
};

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


 const updatePost = async (title, content, thumbnail,accessToken, id) => {
    try {
    const response =  await axios.put(`http://i10b208.p.ssafy.io/api/articles/${id}`,{
        title: title,
        content: content,
        thumbnail: null
    }, {
        headers: {
            'Authorization': `${accessToken}`
        }
    })
    alert('게시글 수정이 완료되었습니다.')

    } catch(error) {
        console.log('게시글 수정 실패',error)
        alert('수정 권한이 없습니다.')
    }
 }
 
const deletePost = async (id, token) => {
    console.log(typeof id)
 try {
    const response = await axios.delete(`http://i10b208.p.ssafy.io/api/articles/${id}`,{
    article_id : id
},{
    headers: {
        'Authorization': token
    }
 })
    alert('삭제되었습니다.')
 } catch (error) {
    console.log(error)
 }};



//   try {
//     const response = await axios.post(
//       "http://i10b208.p.ssafy.io/api/articles/image/{article_id}",
//       {},
//       {
//         headers: {
//           "Content-Type": "multipart/form-data",
//         },
//       }
//     );
//   } catch (error) {
//     console.error("이미지를 저장할 수 없어요", error);
//   }


export {
    deletePost,
    boardList,
    boardSearch,
    boardDetail,
    articleCreate,
    saveImg,
    updatePost
};
