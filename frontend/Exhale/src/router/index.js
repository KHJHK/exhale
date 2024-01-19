import { createRouter, createWebHistory } from 'vue-router';

// auth
import SignUp from '@/components/auth/SignUp.vue';
import Login from '@/components/auth/Login.vue';
import ForgotId from '@/components/auth/ForgotId.vue';
import ForgotPassword from '@/components/auth/ForgotPassword.vue';
import Logout from '@/components/auth/Logout.vue';

// Aphasia Recovery Course
import WaitingArea from '@/components/ARC/WaitingArea.vue';
import FollowUpSpeech from '@/components/ARC/FollowUpSpeech.vue';
import ListeningComprehension from '@/components/ARC/ListeningComprehension.vue';
import Fluency from '@/components/ARC/Fluency.vue';
import Review from '@/components/ARC/Review.vue';

// community
import ReadPost from '@/components/community/ReadPost.vue';
import WritePost from '@/components/community/WritePost.vue';
import PostDetails from '@/components/community/PostDetails.vue';
import AddComment from '@/components/community/AddComment.vue';

// main
import InitialPage from '@/components/main/InitialPage.vue';
import InitialTestPage from '@/components/main/InitialTestPage.vue';
import TestResultPage from '@/components/main/TestResultPage.vue';
import MainPage from '@/components/main/MainPage.vue';
import ARCWaitingPage from '@/components/main/ARCWaitingPage.vue';
import AboutUs from '@/components/main/AboutUs.vue';

// myPage
import ARCReport from '@/components/myPage/ARCReport.vue';
import EditUserInfo from '@/components/myPage/EditUserInfo.vue';
import ChangePassword from '@/components/myPage/ChangePassword.vue';
import Withdrawal from '@/components/myPage/Withdrawal.vue';


// admin
import DeletePost from '@/components/admin/DeletePost.vue';
import FindingUser from '@/components/admin/FindingUser.vue';

// modals
import TestPage from '@/components/modals/TestPage.vue';
import WithdrawalConfirmation from '@/components/modals/WithdrawalConfirmation.vue';
import ResetPasswordModal from '@/components/modals/ResetPassword.vue';
import QuestionResults from '@/components/modals/QuestionResults.vue';

// common


// Import Common components
const history = createWebHistory();
const router = createRouter({
  history,
  routes: [
    // auth
    { path: '/signup', component: SignUp },
    { path: '/login', component: Login },
    { path: '/forgot-id', component: ForgotId },
    { path: '/forgot-password', component: ForgotPassword },
    { path: '/logout', component: Logout },
    
    // Aphasia Recovery Course
    { path: '/waiting-area', component: WaitingArea },
    { path: '/follow-up-speech', component: FollowUpSpeech },
    { path: '/listening-comprehension', component: ListeningComprehension },
    { path: '/fluency', component: Fluency },
    { path: '/review', component: Review },

    // community
    { path: '/read-post', component: ReadPost },
    { path: '/write-post', component: WritePost },
    { path: '/post-details', component: PostDetails },
    { path: '/add-comment', component: AddComment },

    // main
    { path: '/initial-page', component: InitialPage },
    { path: '/initial-test-page', component: InitialTestPage },
    { path: '/test-result-page', component: TestResultPage },
    { path: '/main-page', component: MainPage },
    { path: '/ARC-waiting-page', component: ARCWaitingPage},
    { path: '/about-us', component: AboutUs},

    // mypage
    { path: '/ARC-report', component: ARCReport },
    { path: '/edit-user-profile', component: EditUserInfo },
    { path: '/change-password', component: ChangePassword },
    { path: '/withdrawal', component: Withdrawal },
    { path: '/withdrawal-confirmation', component: WithdrawalConfirmation },

    // admin
    { path: '/delete-post', component: DeletePost },
    { path: '/finding-user', component: FindingUser },

    // modals
    { path: '/test-page', component: TestPage },
    { path: '/withdrawal-confirmation-modal', component: WithdrawalConfirmation},
    { path: '/reset-password-modal', component: ResetPasswordModal },
    { path: '/question-results', component: QuestionResults },


  ],
});

export default router;
