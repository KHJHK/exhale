import axios from "axios";

// const formDataHeader = {"Content-Type":
// "multipart/form-data"};

const posts = [
  {
    "id": "1",
    "title": "제목1",
    "content": "내용1",
    "create_date": "2020-01-01"
  },
  {
    "id": "2",
    "title": "제목2",
    "content": "내용2",
    "create_date": "2020-02-01"
  },
  {
    "id": "3",
    "title": "제목3",
    "content": "내용3",
    "create_date": "2020-03-01"
  },
  {
    "id": "4",
    "title": "제목4",
    "content": "내용4"
  },
  {
    "id": "5",
    "title": "제목5",
    "content": "내용5",
    "create_date": "2020-05-01"
  },
  {
    "id": "6",
    "title": "제목6",
    "content": "내용6",
    "create_date": "2020-01-01"
  },
  {
    "id": "7",
    "title": "제목7",
    "content": "내용7",
    "create_date": "2020-02-01"
  },
  {
    "id": "8",
    "title": "제목8",
    "content": "내용8",
    "create_date": "2020-03-01"
  },
  {
    "id": "9",
    "title": "제목9",
    "content": "내용9",
    "create_date": "2020-04-01"
  },
  {
    "id": "10",
    "title": "제목10",
    "content": "내용10",
    "create_date": "2020-05-01"
  },
  {
    "id": "11",
    "title": "제목11",
    "content": "내용11",
    "create_date": "2020-02-08"
  },
  {
    "id": "12",
    "title": "제목12",
    "content": "내용12",
    "create_date": "2020-03-02"
  },
  {
    "id": "868",
    "title": "dd",
    "content": "asd",
    "create_date": 1706512230020
  },
  {
    "id": "2731",
    "title": "333",
    "content": "3333",
    "create_date": 1706512253471
  },
  {
    "id": "22",
    "title": "되나요>?",
    "content": "이거 되는거만쟌",
    "create_date": 1706516200718
  },
  {
    "id": "76",
    "title": "ㅇㅇㅇㅇ",
    "content": "ㄴㅇㄹㄴㄴㅇ",
    "create_date": 1706516526277
  },
  {
    "id": "fb59",
    "title": "ㅇㅇㅇㅇ",
    "content": "ㅇㅇㅇㅇ",
    "create_date": 1706681789815
  },
  {
    "id": "9646",
    "title": "test",
    "content": "test입니다",
    "create_date": 1706681820291
  }
]




// 리스트를 조회하는 함수
export function getPosts() {
  // axios.HTTP메서드('서버 url(아래에 일단 가상 db.json 서버 url 넣어둠)')
  return axios.get('http://localhost:5000/posts')
}

// 상세데이터 조회하는 함수
export function getPostById(postId) {
  return axios.get(`http://localhost:5000/posts/${postId}`)
}

// 게시글 등록 함수
export function createPost(data) {
  return axios.post('http://localhost:5000/posts', data)
}

// 게시글 수정 함수
export function updatePost(id, data) {
  return axios.put(`http://localhost:5000/posts/${id}`, data)
}

// 게시글 삭제 함수
export function deletePost(id) {
  return axios.delete(`http://localhost:5000/posts/${id}`)
}


export function getComments(postId) {
  return axios.get(`http://localhost:5000/comments?postId=${postId}`)
}

export function createComments(postId, data) {
  const commentData = {...data, postId};
  
  return axios.post(`http://localhost:5000/comments`, commentData)
}