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
import ReadPost from '@/views/community/ReadPost.vue';
import WritePost from '@/views/community/WritePost.vue';
import PostDetails from '@/views/community/PostDetails.vue';
import AddComment from '@/views/community/AddComment.vue';

// main
import InitialPage from '@/views/main/InitialPage.vue';
import InitialTestPage from '@/views/main/InitialTestPage.vue';
import TestResultPage from '@/views/main/TestResultPage.vue';
import MainPage from '@/views/main/MainPage.vue';
import ARCWaitingPage from '@/views/main/ARCWaitingPage.vue';
import AboutUs from '@/views/main/AboutUs.vue';
import Home from '@/views/main/Home.vue';

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
    { path: '/read-post', name: 'ReadPost', component: ReadPost },
    { path: '/write-post', name: 'WritePost', component: WritePost },
    { path: '/post-details', name: 'PostDetails', component: PostDetails },
    { path: '/add-comment', name: 'AddComment', component: AddComment },

    // main
    { path: '/initial-page', name: 'InitialPage', component: InitialPage },
    { path: '/initial-test-page', name: 'InitialTestPage', component: InitialTestPage },
    { path: '/test-result-page', name: 'TestResultPage', component: TestResultPage },
    { path: '/main-page', name: 'MainPage', component: MainPage },
    { path: '/ARC-waiting-page', name:' ARCWaitingPage', component: ARCWaitingPage},
    { path: '/about-us', name: 'AboutUs', component: AboutUs},
    { path: '/home', name: 'Home', component: Home},

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


  ],
});

export default router;
