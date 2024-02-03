import axios from "axios";

const formDataHeader = {"Content-Type":
"multipart/form-data"};



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