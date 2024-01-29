import axios from "axios";

// axios
const posts = [
  {articleId: 1, title: '제목1', content: '내용1', create_date: '2020-01-01'},
  {articleId: 2, title: '제목2', content: '내용2', create_date: '2020-02-01'},
  {articleId: 3, title: '제목3', content: '내용3', create_date: '2020-03-01'},
  {articleId: 4, title: '제목4', content: '내용4', create_date: '2020-04-01'},
  {articleId: 5, title: '제목5', content: '내용5', create_date: '2020-05-01'},
  {articleId: 6, title: '제목6', content: '내용6', create_date: '2020-01-01'},
  {articleId: 7, title: '제목7', content: '내용7', create_date: '2020-02-01'},
  {articleId: 8, title: '제목8', content: '내용8', create_date: '2020-03-01'},
  {articleId: 9, title: '제목9', content: '내용9', create_date: '2020-04-01'},
  {articleId: 10, title: '제목10', content: '내용10', create_date: '2020-05-01'},
  {articleId: 11, title: '제목11', content: '내용11', create_date: '2020-02-08'},
  {articleId: 12, title: '제목12', content: '내용12', create_date: '2020-03-02'}
]

export function getPosts() {
  return posts;
}