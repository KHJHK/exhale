// axios
const posts = [
  {articleId: 1, title: '제목1', content: '내용1', create_date: '2020-01-01'},
  {articleId: 2, title: '제목2', content: '내용2', create_date: '2020-02-01'},
  {articleId: 3, title: '제목3', content: '내용3', create_date: '2020-03-01'},
  {articleId: 4, title: '제목4', content: '내용4', create_date: '2020-04-01'},
  {articleId: 5, title: '제목5', content: '내용5', create_date: '2020-05-01'},
]

export function getPosts() {
  return posts;
}