import { createRouter, createWebHistory } from 'vue-router';

// auth
import SignUp from '@/views/auth/SignUp.vue';
import Login from '@/views/auth/Login.vue';
import ForgotId from '@/views/auth/ForgotId.vue';
import ForgotPassword from '@/views/auth/ForgotPassword.vue';
import Logout from '@/views/auth/Logout.vue';

// Aphasia Recovery Course
import FollowUpSpeech from '@/views/ARC/FollowUpSpeech.vue';
import ListeningComprehension from '@/views/ARC/ListeningComprehension.vue';
import Fluency from '@/views/ARC/Fluency.vue';
import Review from '@/views/ARC/Review.vue';

// community
import PostCreateView from '@/views/community/PostCreateView.vue';
import PostDetailView from '@/views/community/PostDetailView.vue';
import PostEditView from '@/views/community/PostEditView.vue';
import PostWholeListView from '@/views/community/PostWholeListView.vue';
import PostInfoListView from '@/views/community/PostInfoListView.vue';
import PostReviewListView from '@/views/community/PostReviewListView.vue';
import PostStoryListView from '@/views/community/PostStoryListView.vue';

// main
import InitialPage from '@/views/main/InitialPage.vue';
import InitialTestPage from '@/views/main/InitialTestPage.vue';
import TestResultPage from '@/views/main/TestResultPage.vue';
import ARCWaitingPage from '@/views/main/ARCWaitingPage.vue';
import AboutUs from '@/views/main/AboutUs.vue';
import MainPage from '@/views/main/MainPage.vue';

// myPage
import ARCReport from '@/views/myPage/ARCReport.vue';
import EditUserInfo from '@/views/myPage/EditUserInfo.vue';
import ChangePassword from '@/views/myPage/ChangePassword.vue';
import Withdrawal from '@/views/myPage/Withdrawal.vue';


// admin
import DeletePost from '@/views/admin/DeletePost.vue';
import FindingUser from '@/views/admin/FindingUser.vue';

// modals

import WithdrawalConfirmation from '@/components/modals/WithdrawalConfirmation.vue';
import ResetPasswordModal from '@/components/modals/ResetPassword.vue';
import QuestionResults from '@/components/modals/QuestionResults.vue';
import WaitingArea from '@/components/modals/WaitingArea.vue';

// common
import Headers from '@/components/common/Headers.vue';
import Footers from '@/components/common/Footers.vue';

// comments
import CommentsCreate from '@/components/comments/CommentsCreate.vue'
import CommentsDelete from '@/components/comments/CommentsDelete.vue'
import CommentsEdit from '@/components/comments/commentsEdit.vue'
import CommentsReply from '@/components/comments/CommentsReply.vue'

// Import Common views
const history = createWebHistory();
const router = createRouter({
  history,
  routes: [       
    
    // auth
    { path: '/signup', name: 'SignUp', component: SignUp  },
    { path: '/login', name:'Login', component: Login },
    { path: '/forgot-id', name:'ForgotId', component: ForgotId},
    { path: '/forgot-password', name:'ForgotPassword', component: ForgotPassword},
    { path: '/logout', name: 'Logout', component: Logout },
    
    // Aphasia Recovery Course
    { path: '/follow-up-speech', name:' FollowUpSpeech', component: FollowUpSpeech },
    { path: '/listening-comprehension', name:'ListeningComprehension', component: ListeningComprehension },
    { path: '/fluency', name:' Fluency', component: Fluency },
    { path: '/review', name: 'Review', component: Review },

    // community
    { path: '/posts/create', name: 'PostCreateView', component: PostCreateView },
    { path: '/posts/:id', name: 'PostDetailView', component: PostDetailView, 
    children: [
      {path: 'commentscreate', name: 'commentscreate', component: CommentsCreate },
      {path: 'commentsedit', name: 'commentsedit', component: CommentsEdit},
      {path: 'commentsdelete', name: 'commentsdelete', component: CommentsDelete},
      {path: 'commentsreply', name: 'commentsreply', component: CommentsReply}
    ]
  },
    { path: '/posts/:id/edit', name: 'PostEditView', component: PostEditView },
    { path: '/post-whole-list', name: 'PostWholeListView', component: PostWholeListView },
    { path: '/post-info-list', name: 'PostInfoListView', component: PostInfoListView },
    { path: '/post-review-list', name: 'PostReviewListView', component: PostReviewListView },
    { path: '/post-story-list', name: 'PostStoryListView', component: PostStoryListView },

    // main
    { path:'/mainpage', name: 'MainPage', component:MainPage},
    { path: '/', name: 'InitialPage', component: InitialPage },
    { path: '/initial-test-page', name: 'InitialTestPage', component: InitialTestPage },
    { path: '/test-result-page', name: 'TestResultPage', component: TestResultPage },
    { path: '/ARC-waiting-page', name:' ARCWaitingPage', component: ARCWaitingPage},
    { path: '/about-us', name: 'AboutUs', component: AboutUs},
  
    // mypage
    { path: '/ARC-report', name: 'ARCReport', component: ARCReport },
    { path: '/edit-user-profile', name: 'EditUserInfo', component: EditUserInfo },
    { path: '/change-password', name:' ChangePassword', component: ChangePassword },
    { path: '/withdrawal', name: 'Withdrawal', component: Withdrawal },
    
    // admin
    { path: '/delete-post', name: 'DeletePost', component: DeletePost },
    { path: '/finding-user', name: 'FindingUser', component: FindingUser },

    // modals
    { path: '/withdrawal-confirmation-modal', name: 'WithdrawalConfirmation', component: WithdrawalConfirmation},
    { path: '/reset-password-modal', name: 'ResetPasswordModal', component: ResetPasswordModal },
    { path: '/question-results', name: 'QuestionResults', component: QuestionResults },
    { path: '/waiting-area', name: 'WaitingArea', component: WaitingArea },

    // common
    { path: '/Headers', name: 'Headers', component: Headers},
    { path: '/Footers', name: 'Footers', component: Footers},
  ],
});

export default router;
