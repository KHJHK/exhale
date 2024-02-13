import axios from "axios";
const accessToken = localStorage.getItem("JWT_token");

const boardList = async (page, board_id) => {
  return await axios.get(`http://i10b208.p.ssafy.io/api/boards/${board_id}?page=${page}`)
}


//게시글 검색 x
const boardSearch = async (board_id, searchType, searchContent, page) => {
  try {
    console.log('as!!!!!!!!!!!', board_id, searchType, searchContent, page)
    const response = await axios.post('http://i10b208.p.ssafy.io/api/boards/search', {
        board_id : board_id,
        search_type : searchType, // title, content, author
        search_content : searchContent,
        page : page // int 

    }) 
    console.log(response)
   
    } catch (error) {
        console.error('검색을 완료하지 못했습니다.', error)
    }}


//글 상세 정보 조회 
const boardDetail = async (article_id) => {
    
    return await axios.get(`http://i10b208.p.ssafy.io/api/articles/${article_id}`, {
    })  };


//게시글 생성 
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
  console.log('찐함수',file)
 try {
    const response = await axios.post('http://i10b208.p.ssafy.io/api/articles/image', {
    file : file
  }, { 
    headers : {
        "Content-Type": "multipart/form-data"
    }
  }
  )
 } catch (error) {
    console.error('이미지를 저장할 수 없어요', error)
 }}

//게시글 수정 
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
 
 // 게시글 삭제 
 const deletePost = async (id, token) => {
  try {
    const response = await axios.delete(`http://i10b208.p.ssafy.io/api/articles/${id}`, {
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `${token}`
      },
      data: {
        article_id: id
      }
    });
    alert('삭제되었습니다.');
  } catch (error) {
    console.error(error);
  }
};





// 댓글목록
const getComments = async (article_id) => {
return await axios.get(`http://i10b208.p.ssafy.io/api/comments/list/${article_id}`)
};

// 댓글수정
const editComments = async (comment_id, content, token) => {
    try {
      const response = await axios.put(`http://i10b208.p.ssafy.io/api/comments/${comment_id}`, {
        content: content
      }, {
        headers: {
          'Authorization': `${token}`,
          'Content-Type': 'application/json'
        }
      });
      if (response.status === 200) {
        alert('수정되었습니다.');
        location.reload()
      }
    } catch (error) {
      console.error(error);
    }
  };

// 댓글 삭제
const deleteComments = async (comment_id, token) => {
  console.log(token)
  try {
  const response = await axios.delete(`http://i10b208.p.ssafy.io/api/comments/${comment_id}`, { 
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `${token}`
    }
  });
  if (response.status === 200) {
    alert('삭제되었습니다.');
    // location.reload()
   
  }
} catch (error) {
  console.error(error);
}}


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
    updatePost,
    getComments,
    editComments,
    deleteComments,
};
