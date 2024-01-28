import axios from "axios";

// axios
const posts = [
  {id: 1, title: '제목1', content: '내용1', create_date: '2020-01-01'},
  {id: 2, title: '제목2', content: '내용2', create_date: '2020-02-01'},
  {id: 3, title: '제목3', content: '내용3', create_date: '2020-03-01'},
  {id: 4, title: '제목4', content: '내용4', create_date: '2020-04-01'},
  {id: 5, title: '제목5', content: '내용5', create_date: '2020-05-01'},
  {id: 6, title: '제목6', content: '내용6', create_date: '2020-01-01'},
  {id: 7, title: '제목7', content: '내용7', create_date: '2020-02-01'},
  {id: 8, title: '제목8', content: '내용8', create_date: '2020-03-01'},
  {id: 9, title: '제목9', content: '내용9', create_date: '2020-04-01'},
  {id: 10, title: '제목10', content: '내용10', create_date: '2020-05-01'},
  {id: 11, title: '제목11', content: '내용11', create_date: '2020-02-08'},
  {id: 12, title: '제목12', content: '내용12', create_date: '2020-03-02'}
]

// 리스트를 조회하는 함수
export function getPosts() {
  // axios.HTTP메서드('서버 url(아래에 일단 가상 db.json 서버 url 넣어둠)')
  return axios.get('http://localhost:5000/posts')
}

// 상세데이터 조회하는 함수
export function getPostById(id) {
  return axios.get(`http://localhost:5000/posts/${id}`)
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