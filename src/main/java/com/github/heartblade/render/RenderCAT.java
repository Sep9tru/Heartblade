package com.github.heartblade.render;

import com.github.heartblade.Entityadd.EntitySummonedBlade_flake;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderCAT extends Render {
    private static double[][] dVec = new double[][] {
            { -29.5747D, 0.0D, -209.4692D }, { -63.8415D, 0.0D, -182.0565D }, { 26.9376D, 0.0D, -188.8279D }, { 6.3064D, 0.0D, -278.8316D }, { 34.3655D, 0.0D, -93.2845D }, { 76.1149D, 0.0D, -200.8026D }, { -4.1153D, 0.0D, -132.5763D }, { 128.5629D, 0.0D, -148.7683D }, { 137.2516D, 0.0D, -101.4414D }, { 165.5407D, 0.0D, -139.2845D },
            { 109.6401D, 0.0D, -61.094D }, { 249.9051D, 0.0D, -142.3526D }, { 201.2664D, 0.0D, -107.0108D }, { 69.5476D, -0.9274D, -22.474D }, { 213.8185D, 0.0D, -36.482D }, { 56.6843D, 0.0D, -90.6823D }, { 126.3494D, 0.0D, 65.2945D }, { 170.6334D, 0.0D, 147.9376D }, { 43.8542D, -12.9838D, 88.9944D }, { 48.2538D, 0.0D, 47.142D },
            { 130.8861D, 0.0D, 152.3008D }, { 57.5099D, 0.0D, 60.4809D }, { 196.1338D, 0.0D, 33.1364D }, { -31.7734D, 0.0D, 86.5123D }, { -70.28D, 0.0D, 201.172D }, { -0.4716D, 0.0D, 279.2006D }, { 69.6761D, 0.0D, 182.4204D }, { 10.7686D, 0.0D, 135.4166D }, { 12.4912D, 0.0D, 212.1255D }, { -193.8171D, 0.0D, 105.2177D },
            { 235.8782D, 0.0D, 128.5536D }, { 193.2347D, 0.0D, 106.3492D }, { -121.6701D, 0.0D, 81.832D }, { -207.9836D, 0.0D, 36.3081D }, { -244.0656D, 0.0D, 142.7247D }, { -159.7059D, 0.0D, 139.6537D }, { -14.1392D, 0.0D, -233.0412D }, { -76.3913D, 0.0D, 16.4809D }, { -53.7638D, 0.0D, -93.696D }, { -51.4035D, 0.0D, -60.1116D },
            { -135.0579D, 0.0D, -163.593D }, { -120.5147D, 0.0D, -64.9252D }, { -164.7986D, 0.0D, -147.5684D }, { -231.6364D, 0.0D, -133.8585D }, { -196.5724D, 0.0D, -86.3729D }, { -190.6801D, 0.0D, -32.9448D }, { -62.5393D, 0.0D, 11.0488D }, { -151.679D, -29.6773D, 31.951D }, { -346.4124D, -29.6773D, -60.7098D }, { -402.5139D, -29.6773D, -68.0798D },
            { -242.7592D, -29.6773D, -128.0963D }, { -344.1744D, -29.6773D, -233.8953D }, { -449.9332D, -29.6773D, -234.9687D }, { -399.9886D, -29.6773D, -252.8536D }, { -483.6381D, -29.6773D, -278.3009D }, { -102.9706D, -29.6773D, -117.3511D }, { -92.7004D, -29.6773D, -143.9922D }, { -115.6773D, -29.6773D, -327.0094D }, { -138.5892D, -29.6773D, -382.5794D }, { -24.9569D, -29.6773D, -139.1586D },
            { -24.1899D, -29.6773D, -283.573D }, { 78.489D, -29.6773D, -421.5139D }, { 27.9156D, -29.6773D, -368.6137D }, { 9.5235D, -29.6773D, -543.7021D }, { 55.6209D, -29.6773D, -159.4355D }, { 72.8174D, -29.6773D, -204.5942D }, { 47.3904D, -29.6773D, -324.8916D }, { 2.6068D, -29.6773D, -233.6399D }, { 43.6366D, -29.6773D, -114.4002D }, { 117.3163D, -29.6773D, -196.4678D },
            { 264.7759D, -29.6773D, -197.9149D }, { 383.7823D, -29.6773D, -255.041D }, { 15.3134D, -37.5603D, -149.4255D }, { 86.7477D, -29.6773D, -86.0232D }, { 145.442D, -29.6773D, -81.7127D }, { 108.8055D, -29.6773D, -118.41D }, { 265.8313D, -29.6773D, -313.6634D }, { 229.5178D, -29.6773D, -266.6532D }, { 157.5139D, -29.6773D, -31.5818D }, { 335.1782D, -29.6773D, -126.4167D },
            { 390.9114D, -29.6773D, -136.7007D }, { 151.1606D, -29.6773D, -20.993D }, { 484.5757D, -29.6773D, -277.4012D }, { 165.1656D, -29.6773D, -14.7417D }, { 157.5139D, -29.6773D, 31.951D }, { 352.2474D, -29.6773D, 61.0796D }, { 408.4142D, -29.6773D, 68.0849D }, { 151.1606D, -29.6773D, 21.3622D }, { 369.7388D, -29.6773D, 256.4403D }, { 405.2674D, -29.6773D, 182.4949D },
            { 394.7743D, -29.6773D, 146.7394D }, { 88.9525D, -29.6773D, 112.3453D }, { 116.3711D, -37.0966D, 122.9812D }, { 250.9861D, -29.6773D, 295.4735D }, { 118.2209D, -29.6773D, 184.8119D }, { -3.6887D, -29.6773D, 544.0719D }, { -31.9156D, -29.6773D, 443.094D }, { 3.4079D, -29.6773D, 235.8861D }, { -41.5555D, -29.6773D, 325.2606D }, { 42.8076D, -29.6773D, 148.043D },
            { 33.0357D, -29.6773D, 291.6988D }, { 121.512D, -29.6773D, 327.3783D }, { 144.4232D, -29.6773D, 382.9268D }, { 47.9429D, -29.6773D, 432.1321D }, { 66.2545D, -29.6773D, 408.8476D }, { 23.6327D, -29.6773D, 478.7175D }, { -123.5192D, -29.6773D, 96.3949D }, { 3.0258D, -29.6773D, 114.0685D }, { 81.617D, -29.6773D, 64.5058D }, { -16.4966D, -29.6773D, -478.9394D },
            { 41.2497D, -29.6773D, -446.9366D }, { -5.6433D, -29.6773D, 376.3011D }, { -72.6541D, -29.6773D, 421.8833D }, { -66.9845D, -29.6773D, 204.9721D }, { -38.5421D, -29.6773D, 148.3112D }, { -81.8917D, -29.6773D, 114.063D }, { -113.1204D, -14.8386D, 134.4944D }, { -258.941D, -29.6773D, 198.2843D }, { -235.0894D, -29.6773D, 138.3257D }, { -314.0849D, -29.6773D, 272.0519D },
            { -413.9949D, -29.6773D, 252.2738D }, { -478.5275D, -29.6773D, 278.0383D }, { -383.9307D, -29.6773D, 180.1608D }, { 19.974D, -59.3546D, -233.0412D }, { 35.4095D, -59.3546D, -209.4694D }, { 69.6763D, -59.3546D, -182.0567D }, { -21.1028D, -59.3546D, -188.828D }, { -0.4716D, -59.3546D, -278.8316D }, { -28.5306D, -59.3546D, -93.2845D }, { -70.28D, -59.3546D, -200.8027D },
            { -122.7282D, -59.3546D, -148.7684D }, { -159.7059D, -59.3546D, -139.2846D }, { -131.417D, -59.3546D, -101.4414D }, { -103.8054D, -59.3546D, -61.0941D }, { -244.0703D, -59.3546D, -142.3527D }, { -195.4318D, -59.3546D, -107.0109D }, { -207.9836D, -59.3546D, -36.4821D }, { -63.934D, -59.3546D, -22.7913D }, { -50.8494D, -59.3546D, -90.6823D }, { -120.5147D, -59.3546D, 65.2944D },
            { -164.7986D, -59.3546D, 147.9376D }, { -187.4001D, -59.3546D, 106.3492D }, { -38.0194D, -46.3708D, 88.9943D }, { -42.419D, -59.3546D, 47.1419D }, { -125.0515D, -59.3546D, 152.3008D }, { -51.6751D, -59.3546D, 60.4809D }, { -190.299D, -59.3546D, 33.1363D }, { 37.6082D, -59.3546D, 86.5123D }, { 76.1149D, -59.3546D, 201.172D }, { -4.9338D, -59.3546D, 135.4166D },
            { 6.3063D, -59.3546D, 279.2005D }, { -63.8413D, -59.3546D, 182.4203D }, { -6.6564D, -59.3546D, 212.1253D }, { 199.6519D, -59.3546D, 105.2176D }, { -230.0433D, -59.3546D, 128.5535D }, { 127.5048D, -59.3546D, 81.832D }, { 213.8185D, -59.3546D, 36.8512D }, { 249.9004D, -59.3546D, 142.7247D }, { 165.5406D, -59.3546D, 139.6536D }, { 82.226D, -59.3546D, 16.4809D },
            { 57.2383D, -59.3546D, -60.1117D }, { 140.8925D, -59.3546D, -163.593D }, { 126.3494D, -59.3546D, -64.9253D }, { 170.6332D, -59.3546D, -147.5685D }, { 237.4714D, -59.3546D, -133.8586D }, { 202.4073D, -59.3546D, -86.3729D }, { 196.515D, -59.3546D, -32.9449D }, { 87.9943D, -52.6888D, 3.0824D }, { -1.3364D, -29.6773D, 13.8296D }, { 165.1656D, -29.6773D, 15.1109D },
            { 210.8755D, -29.6773D, -0.2714D }, { 352.2471D, -29.6773D, -60.7098D }, { 408.2493D, -29.6773D, -68.146D }, { 248.594D, -29.6773D, -128.0963D }, { 455.7679D, -29.6773D, -234.9688D }, { 318.3975D, -29.6773D, -271.2859D }, { 108.8055D, -29.6773D, -117.3512D }, { 98.5353D, -29.6773D, -143.9922D }, { 66.2545D, -29.6773D, -408.495D }, { 121.512D, -29.6773D, -327.0095D },
            { 144.424D, -29.6773D, -382.5796D }, { 30.0247D, -29.6773D, -283.573D }, { 3.228D, -29.6773D, -233.6399D }, { -50.0041D, -29.6773D, -432.6851D }, { -3.6887D, -29.6773D, -543.7021D }, { 50.0006D, -29.6773D, -429.9471D }, { -49.7861D, -29.6773D, -159.4355D }, { -66.9827D, -29.6773D, -204.5943D }, { -41.5555D, -29.6773D, -324.8916D }, { -54.2794D, -29.6773D, -98.0921D },
            { -18.7045D, -1.8548D, -62.5379D }, { -37.8018D, -29.6773D, -114.4003D }, { -111.4815D, -29.6773D, -196.4678D }, { -258.941D, -29.6773D, -197.9149D }, { -312.5627D, -29.6773D, -271.2859D }, { -363.5098D, -29.6773D, -265.4339D }, { -478.5366D, -29.6773D, -277.6637D }, { -75.3468D, -29.6773D, -89.3395D }, { -139.6071D, -29.6773D, -81.7127D }, { -102.9706D, -29.6773D, -118.4101D },
            { -259.9965D, -29.6773D, -313.6634D }, { -223.683D, -29.6773D, -266.6532D }, { -151.6791D, -29.6773D, -31.5818D }, { -408.1929D, -29.6773D, -70.231D }, { -327.1019D, -29.6773D, -145.6466D }, { -385.0767D, -29.6773D, -136.7008D }, { -152.688D, -29.4527D, 14.4721D }, { -346.4126D, -29.6773D, 61.0795D }, { -402.5794D, -29.6773D, 68.0848D }, { -324.012D, -29.6773D, 288.1997D },
            { -352.084D, -29.6773D, 241.9342D }, { -395.0408D, -29.6773D, 181.9085D }, { -474.0293D, -29.6773D, 265.3342D }, { -388.9396D, -29.6773D, 146.7394D }, { -105.6651D, -29.6773D, 169.5921D }, { -83.1177D, -29.6773D, 112.3453D }, { -101.29D, -29.6773D, 114.0363D }, { -103.5001D, -29.6773D, 117.7203D }, { -234.4171D, -29.6773D, 281.2479D }, { 9.5234D, -29.6773D, 544.0719D },
            { 47.3903D, -29.6773D, 325.2605D }, { 44.3769D, -29.6773D, 148.3112D }, { -36.9728D, -29.6773D, 148.043D }, { -27.2009D, -29.6773D, 291.6986D }, { -115.6773D, -29.6773D, 327.3781D }, { -138.5886D, -29.6773D, 382.9268D }, { 2.4269D, -29.6773D, 235.886D }, { -42.1081D, -29.6773D, 432.132D }, { -60.4197D, -29.6773D, 408.8474D }, { -17.7979D, -29.6773D, 478.7174D },
            { 2.809D, -29.6773D, 114.0684D }, { 37.7504D, -29.6773D, 443.094D }, { -428.3586D, -29.6773D, 235.4945D }, { -75.7822D, -29.6773D, 64.5057D }, { 411.5043D, -29.6773D, -194.3455D }, { -404.7252D, -29.6773D, -188.583D }, { -417.4272D, -29.6773D, -256.7262D }, { -337.2145D, -29.6773D, -219.2061D }, { 22.3315D, -29.6773D, -478.9395D }, { 11.4781D, -29.6773D, 376.3009D },
            { 78.4889D, -29.6773D, 421.8833D }, { 72.8193D, -29.6773D, 204.972D }, { 85.4849D, -29.6773D, 144.3199D }, { 87.7265D, -29.6773D, 114.063D }, { 264.7758D, -29.6773D, 198.2842D }, { 240.9242D, -29.6773D, 138.3258D }, { 318.3975D, -29.6773D, 271.6548D }, { 335.3328D, -29.6773D, 203.9168D }, { 355.4675D, -29.6773D, 248.208D }, { 424.6312D, -29.6773D, 250.1372D },
            { 477.4114D, -29.6773D, 264.8867D } };

    private static int[][] nVecPos = new int[][] {
            { 37, 32, 33 }, { 37, 23, 32 }, { 32, 35, 29 }, { 23, 116, 32 }, { 18, 27, 23 }, { 27, 24, 23 }, { 18, 26, 27 }, { 18, 168, 167 }, { 16, 21, 167 }, { 16, 20, 21 },
            { 31, 17, 16 }, { 167, 22, 16 }, { 168, 13, 167 }, { 10, 14, 13 }, { 8, 12, 10 }, { 8, 9, 12 }, { 13, 7, 10 }, { 6, 4, 190 }, { 6, 5, 4 }, { 36, 3, 2 },
            { 6, 0, 2 }, { 190, 1, 6 }, { 13, 168, 190 }, { 41, 39, 206 }, { 41, 40, 39 }, { 44, 42, 41 }, { 206, 45, 41 }, { 23, 46, 168 }, { 168, 206, 190 }, { 18, 23, 168 },
            { 208, 118, 47 }, { 118, 106, 47 }, { 233, 206, 106 }, { 233, 168, 206 }, { 50, 206, 198 }, { 50, 48, 206 }, { 50, 49, 48 }, { 204, 205, 50 }, { 53, 52, 235 }, { 53, 54, 52 },
            { 235, 204, 51 }, { 50, 194, 204 }, { 55, 200, 50 }, { 198, 55, 50 }, { 206, 197, 198 }, { 168, 13, 190 }, { 67, 57, 59 }, { 67, 58, 57 }, { 67, 66, 60 }, { 66, 183, 60 },
            { 109, 110, 63 }, { 66, 62, 183 }, { 66, 61, 62 }, { 65, 180, 67 }, { 64, 65, 67 }, { 59, 64, 67 }, { 59, 13, 68 }, { 167, 73, 13 }, { 73, 167, 74 }, { 173, 75, 74 },
            { 173, 77, 75 }, { 173, 76, 77 }, { 71, 175, 70 }, { 173, 71, 70 }, { 71, 234, 82 }, { 173, 79, 71 }, { 79, 80, 71 }, { 74, 78, 173 }, { 78, 172, 173 }, { 167, 81, 74 },
            { 167, 170, 83 }, { 108, 87, 167 }, { 108, 92, 87 }, { 245, 84, 92 }, { 245, 85, 84 }, { 245, 86, 85 }, { 247, 90, 245 }, { 249, 88, 250 }, { 88, 89, 247 }, { 245, 246, 247 },
            { 92, 93, 245 }, { 168, 18, 108 }, { 18, 242, 108 }, { 107, 99, 18 }, { 107, 114, 99 }, { 114, 97, 99 }, { 97, 101, 99 }, { 97, 102, 101 }, { 98, 111, 100 }, { 97, 98, 100 },
            { 111, 104, 100 }, { 105, 96, 95 }, { 98, 112, 111 }, { 113, 225, 97 }, { 114, 113, 97 }, { 107, 142, 114 }, { 168, 233, 142 }, { 233, 214, 142 }, { 118, 116, 106 }, { 118, 218, 116 },
            { 210, 119, 117 }, { 118, 210, 117 }, { 120, 122, 121 }, { 118, 213, 210 }, { 13, 59, 190 }, { 168, 167, 13 }, { 206, 168, 190 }, { 168, 108, 167 }, { 142, 18, 168 }, { 18, 142, 107 },
            { 159, 155, 156 }, { 159, 147, 155 }, { 155, 158, 153 }, { 147, 92, 155 }, { 142, 149, 147 }, { 149, 148, 147 }, { 142, 151, 149 }, { 142, 168, 206 }, { 139, 145, 206 }, { 139, 144, 145 },
            { 141, 140, 139 }, { 206, 146, 139 }, { 168, 137, 206 }, { 133, 136, 137 }, { 132, 135, 133 }, { 132, 131, 135 }, { 137, 130, 133 }, { 72, 129, 128 }, { 123, 127, 126 }, { 72, 124, 126 },
            { 137, 168, 72 }, { 162, 160, 167 }, { 162, 161, 160 }, { 165, 163, 162 }, { 167, 166, 162 }, { 147, 167, 168 }, { 168, 167, 72 }, { 142, 147, 168 }, { 86, 245, 84 }, { 245, 92, 84 },
            { 108, 87, 92 }, { 108, 167, 87 }, { 167, 170, 169 }, { 167, 74, 81 }, { 167, 73, 74 }, { 173, 78, 74 }, { 173, 171, 78 }, { 173, 172, 171 }, { 71, 80, 173 }, { 71, 174, 234 },
            { 71, 82, 174 }, { 173, 175, 71 }, { 176, 76, 173 }, { 74, 176, 173 }, { 168, 190, 13 }, { 182, 179, 72 }, { 182, 180, 179 }, { 182, 188, 181 }, { 183, 178, 181 }, { 188, 183, 181 },
            { 238, 183, 184 }, { 187, 58, 182 }, { 186, 187, 182 }, { 72, 186, 182 }, { 72, 190, 191 }, { 190, 206, 197 }, { 206, 198, 197 }, { 50, 199, 198 }, { 50, 201, 199 }, { 50, 200, 201 },
            { 204, 194, 193 }, { 50, 204, 193 }, { 235, 237, 204 }, { 237, 236, 195 }, { 236, 235, 196 }, { 198, 202, 50 }, { 202, 203, 50 }, { 233, 206, 168 }, { 233, 106, 206 }, { 118, 206, 106 },
            { 118, 207, 206 }, { 118, 208, 207 }, { 210, 213, 118 }, { 232, 210, 212 }, { 118, 209, 210 }, { 106, 217, 118 }, { 217, 218, 118 }, { 233, 216, 106 }, { 168, 142, 233 }, { 142, 214, 233 },
            { 230, 222, 142 }, { 230, 221, 222 }, { 221, 226, 222 }, { 226, 224, 222 }, { 226, 225, 224 }, { 220, 239, 223 }, { 226, 220, 223 }, { 239, 228, 223 }, { 229, 231, 219 }, { 220, 240, 239 },
            { 241, 102, 226 }, { 221, 241, 226 }, { 230, 18, 221 }, { 168, 108, 18 }, { 108, 242, 18 }, { 245, 93, 92 }, { 247, 246, 244 }, { 245, 247, 244 }, { 249, 89, 250 }, { 245, 90, 247 },
            { 190, 72, 13 }, { 167, 13, 73 }, { 168, 206, 190 }, { 167, 168, 13 }, { 108, 168, 167 }, { 18, 142, 168 }, { 142, 18, 230 }, { 23, 37, 47 }, { 23, 47, 106 }, { 46, 23, 106 },
            { 46, 106, 206 }, { 168, 46, 206 }, { 45, 206, 48 }, { 45, 48, 49 }, { 41, 45, 49 }, { 41, 49, 50 }, { 44, 41, 50 }, { 44, 50, 205 }, { 44, 205, 204 }, { 44, 204, 235 },
            { 43, 44, 235 }, { 43, 235, 52 }, { 43, 52, 54 }, { 43, 54, 53 }, { 44, 43, 53 }, { 44, 53, 51 }, { 44, 51, 204 }, { 42, 44, 204 }, { 42, 204, 194 }, { 41, 42, 194 },
            { 41, 194, 50 }, { 40, 41, 50 }, { 40, 50, 200 }, { 39, 40, 200 }, { 39, 200, 55 }, { 206, 39, 55 }, { 206, 55, 198 }, { 197, 190, 206 }, { 197, 206, 198 }, { 190, 197, 206 },
            { 190, 189, 56 }, { 56, 38, 190 }, { 38, 56, 192 }, { 56, 38, 192 }, { 190, 38, 56 }, { 190, 56, 189 }, { 1, 190, 59 }, { 1, 59, 57 }, { 1, 57, 58 }, { 67, 6, 1 },
            { 67, 1, 58 }, { 6, 67, 60 }, { 0, 6, 60 }, { 0, 60, 183 }, { 2, 0, 183 }, { 36, 2, 183 }, { 36, 183, 109 }, { 3, 36, 109 }, { 3, 109, 63 }, { 2, 3, 63 },
            { 2, 63, 110 }, { 2, 110, 183 }, { 2, 183, 62 }, { 2, 62, 61 }, { 2, 61, 66 }, { 6, 2, 66 }, { 6, 66, 67 }, { 5, 6, 67 }, { 5, 67, 180 }, { 4, 5, 180 },
            { 4, 180, 65 }, { 4, 65, 64 }, { 190, 4, 64 }, { 190, 64, 59 }, { 13, 190, 59 }, { 13, 59, 68 }, { 13, 72, 177 }, { 15, 13, 177 }, { 15, 177, 69 }, { 15, 69, 177 },
            { 13, 15, 177 }, { 13, 177, 72 }, { 13, 73, 74 }, { 13, 74, 75 }, { 7, 13, 75 }, { 7, 75, 77 }, { 7, 77, 76 }, { 10, 7, 76 }, { 10, 76, 173 }, { 8, 10, 173 },
            { 8, 173, 70 }, { 9, 8, 70 }, { 9, 70, 175 }, { 12, 9, 175 }, { 12, 175, 71 }, { 11, 12, 71 }, { 11, 71, 82 }, { 12, 11, 82 }, { 12, 82, 234 }, { 12, 234, 71 },
            { 80, 12, 71 }, { 12, 80, 79 }, { 10, 12, 79 }, { 10, 79, 173 }, { 14, 10, 173 }, { 14, 173, 172 }, { 13, 14, 172 }, { 13, 172, 78 }, { 13, 78, 74 }, { 13, 74, 81 },
            { 13, 81, 167 }, { 167, 83, 170 }, { 167, 87, 92 }, { 167, 92, 84 }, { 22, 167, 84 }, { 22, 84, 85 }, { 22, 85, 86 }, { 16, 22, 86 }, { 16, 86, 245 }, { 31, 16, 245 },
            { 31, 245, 90 }, { 31, 90, 247 }, { 31, 247, 89 }, { 30, 31, 89 }, { 30, 89, 249 }, { 30, 249, 250 }, { 31, 30, 250 }, { 31, 250, 88 }, { 31, 88, 247 }, { 17, 31, 247 },
            { 17, 247, 246 }, { 16, 17, 246 }, { 16, 246, 245 }, { 20, 16, 245 }, { 20, 245, 93 }, { 21, 20, 93 }, { 21, 93, 92 }, { 167, 21, 92 }, { 19, 167, 92 }, { 19, 92, 108 },
            { 167, 19, 108 }, { 18, 167, 108 }, { 18, 108, 91 }, { 18, 91, 108 }, { 18, 108, 242 }, { 18, 242, 94 }, { 18, 94, 242 }, { 26, 18, 99 }, { 26, 99, 101 }, { 26, 101, 102 },
            { 27, 26, 102 }, { 27, 102, 97 }, { 27, 97, 100 }, { 28, 27, 100 }, { 28, 100, 104 }, { 111, 28, 104 }, { 28, 111, 103 }, { 28, 103, 105 }, { 25, 28, 105 }, { 25, 105, 95 },
            { 28, 25, 95 }, { 28, 95, 96 }, { 28, 96, 111 }, { 28, 111, 112 }, { 28, 112, 98 }, { 27, 28, 98 }, { 27, 98, 97 }, { 24, 27, 97 }, { 24, 97, 225 }, { 23, 24, 225 },
            { 23, 225, 113 }, { 23, 113, 114 }, { 23, 114, 142 }, { 23, 142, 214 }, { 23, 214, 233 }, { 23, 233, 115 }, { 23, 115, 233 }, { 23, 233, 106 }, { 23, 106, 116 }, { 32, 116, 218 },
            { 32, 218, 118 }, { 32, 118, 117 }, { 35, 32, 117 }, { 35, 117, 119 }, { 29, 35, 119 }, { 29, 119, 210 }, { 29, 210, 120 }, { 34, 29, 120 }, { 34, 120, 121 }, { 29, 34, 121 },
            { 29, 121, 122 }, { 29, 122, 210 }, { 29, 210, 213 }, { 32, 29, 213 }, { 32, 213, 118 }, { 33, 32, 118 }, { 33, 118, 208 }, { 37, 33, 208 }, { 37, 208, 47 }, { 28, 105, 103 },
            { 28, 103, 111 }, { 28, 111, 96 }, { 28, 96, 105 }, { 30, 31, 88 }, { 30, 88, 249 }, { 31, 30, 249 }, { 31, 249, 89 }, { 31, 89, 88 }, { 29, 210, 122 }, { 29, 122, 120 },
            { 29, 120, 210 }, { 18, 23, 107 }, { 23, 18, 107 }, { 23, 107, 142 }, { 23, 142, 107 }, { 167, 19, 108 }, { 19, 167, 108 }, { 167, 19, 108 }, { 19, 167, 108 }, { 167, 108, 168 },
            { 167, 168, 108 }, { 168, 23, 233 }, { 23, 168, 233 }, { 190, 13, 59 }, { 13, 190, 59 }, { 12, 234, 71 }, { 12, 71, 234 }, { 44, 235, 51 }, { 43, 44, 51 }, { 43, 51, 53 },
            { 235, 44, 43 }, { 235, 43, 53 }, { 2, 183, 110 }, { 36, 2, 110 }, { 36, 110, 109 }, { 36, 109, 183 }, { 2, 36, 183 }, { 147, 159, 84 }, { 147, 84, 92 }, { 167, 147, 92 },
            { 167, 92, 87 }, { 167, 169, 170 }, { 167, 81, 74 }, { 167, 74, 78 }, { 166, 167, 78 }, { 166, 78, 171 }, { 166, 171, 172 }, { 162, 166, 172 }, { 162, 172, 173 }, { 165, 162, 173 },
            { 165, 173, 80 }, { 165, 80, 71 }, { 165, 71, 234 }, { 164, 165, 234 }, { 164, 234, 174 }, { 164, 174, 82 }, { 164, 82, 71 }, { 165, 164, 71 }, { 163, 165, 71 }, { 163, 71, 175 },
            { 162, 163, 175 }, { 162, 175, 173 }, { 161, 162, 173 }, { 161, 173, 76 }, { 160, 161, 76 }, { 160, 76, 176 }, { 74, 167, 160 }, { 74, 160, 176 }, { 72, 167, 74 }, { 72, 74, 73 },
            { 72, 73, 13 }, { 72, 177, 69 }, { 72, 69, 177 }, { 125, 72, 179 }, { 125, 179, 180 }, { 72, 125, 180 }, { 72, 180, 182 }, { 72, 182, 181 }, { 124, 72, 181 }, { 124, 181, 178 },
            { 126, 124, 178 }, { 126, 178, 183 }, { 123, 126, 183 }, { 123, 183, 185 }, { 123, 185, 238 }, { 127, 123, 238 }, { 127, 238, 184 }, { 126, 127, 184 }, { 126, 184, 183 }, { 188, 126, 183 },
            { 72, 126, 188 }, { 72, 188, 182 }, { 129, 72, 182 }, { 129, 182, 58 }, { 128, 129, 58 }, { 128, 58, 187 }, { 128, 187, 186 }, { 72, 128, 186 }, { 137, 72, 191 }, { 137, 191, 190 },
            { 137, 190, 189 }, { 137, 189, 56 }, { 138, 137, 56 }, { 138, 56, 192 }, { 138, 192, 56 }, { 137, 138, 56 }, { 137, 56, 189 }, { 137, 189, 190 }, { 137, 190, 197 }, { 137, 197, 198 },
            { 137, 198, 199 }, { 130, 137, 199 }, { 130, 199, 201 }, { 130, 201, 200 }, { 133, 130, 200 }, { 133, 200, 50 }, { 193, 132, 133 }, { 193, 133, 50 }, { 131, 132, 193 }, { 131, 193, 194 },
            { 135, 131, 194 }, { 135, 194, 204 }, { 135, 204, 237 }, { 135, 237, 195 }, { 135, 195, 236 }, { 134, 135, 236 }, { 134, 236, 196 }, { 135, 134, 196 }, { 135, 196, 235 }, { 135, 235, 204 },
            { 135, 204, 205 }, { 135, 205, 204 }, { 133, 135, 204 }, { 133, 204, 50 }, { 136, 133, 50 }, { 136, 50, 203 }, { 137, 136, 203 }, { 137, 203, 202 }, { 137, 202, 198 }, { 137, 198, 206 },
            { 146, 206, 207 }, { 146, 207, 208 }, { 139, 146, 208 }, { 139, 208, 118 }, { 141, 139, 118 }, { 141, 118, 213 }, { 141, 213, 210 }, { 141, 210, 211 }, { 154, 141, 211 }, { 154, 211, 232 },
            { 154, 232, 212 }, { 141, 154, 212 }, { 141, 212, 210 }, { 140, 141, 210 }, { 140, 210, 209 }, { 139, 140, 209 }, { 139, 209, 118 }, { 218, 144, 139 }, { 218, 139, 118 }, { 145, 144, 218 },
            { 145, 218, 217 }, { 206, 145, 217 }, { 206, 217, 106 }, { 143, 206, 106 }, { 143, 106, 216 }, { 143, 216, 233 }, { 206, 143, 233 }, { 142, 206, 233 }, { 142, 233, 215 }, { 142, 215, 233 },
            { 142, 233, 214 }, { 151, 142, 222 }, { 151, 222, 224 }, { 151, 224, 225 }, { 149, 151, 225 }, { 149, 225, 226 }, { 149, 226, 223 }, { 152, 149, 223 }, { 152, 223, 228 }, { 152, 228, 239 },
            { 152, 239, 227 }, { 152, 227, 229 }, { 150, 152, 229 }, { 150, 229, 219 }, { 152, 150, 219 }, { 152, 219, 231 }, { 152, 231, 239 }, { 152, 239, 240 }, { 152, 240, 220 }, { 149, 152, 220 },
            { 149, 220, 226 }, { 148, 149, 226 }, { 148, 226, 102 }, { 147, 148, 102 }, { 147, 102, 241 }, { 147, 241, 221 }, { 147, 221, 18 }, { 147, 18, 242 }, { 147, 242, 94 }, { 147, 94, 242 },
            { 147, 242, 108 }, { 147, 108, 243 }, { 147, 243, 108 }, { 147, 108, 92 }, { 155, 92, 93 }, { 155, 93, 245 }, { 155, 245, 244 }, { 158, 155, 244 }, { 158, 244, 246 }, { 153, 158, 246 },
            { 153, 246, 247 }, { 153, 247, 248 }, { 249, 153, 248 }, { 157, 153, 249 }, { 157, 249, 250 }, { 153, 157, 250 }, { 153, 250, 89 }, { 153, 89, 247 }, { 153, 247, 90 }, { 155, 153, 90 },
            { 155, 90, 245 }, { 156, 155, 245 }, { 156, 245, 86 }, { 159, 156, 86 }, { 159, 86, 84 }, { 152, 229, 227 }, { 152, 227, 239 }, { 152, 239, 231 }, { 152, 231, 229 }, { 154, 141, 210 },
            { 154, 210, 232 }, { 141, 154, 232 }, { 141, 232, 211 }, { 141, 211, 210 }, { 153, 248, 247 }, { 153, 247, 89 }, { 153, 89, 249 }, { 153, 249, 248 }, { 142, 147, 230 }, { 147, 142, 230 },
            { 147, 230, 18 }, { 147, 18, 230 }, { 206, 143, 233 }, { 143, 206, 233 }, { 206, 143, 233 }, { 143, 206, 233 }, { 206, 233, 168 }, { 206, 168, 233 }, { 168, 147, 108 }, { 147, 168, 108 },
            { 137, 190, 206 }, { 137, 206, 190 }, { 72, 137, 190 }, { 137, 72, 190 }, { 135, 235, 236 }, { 135, 236, 237 }, { 135, 237, 235 }, { 165, 234, 71 }, { 164, 165, 71 }, { 165, 164, 71 },
            { 165, 71, 234 }, { 123, 126, 183 }, { 123, 183, 238 }, { 123, 238, 185 }, { 126, 123, 185 }, { 126, 185, 183 } };

    public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
        if (entity instanceof EntitySummonedBlade_flake)
            doDriveRender((EntitySummonedBlade_flake)entity, d0, d1, d2, f, f1);
    }

    protected ResourceLocation getEntityTexture(Entity var1) {
        return null;
    }

    private void doDriveRender(EntitySummonedBlade_flake entity, double dX, double dY, double dZ, float f, float f1) {
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        int color = entity.getColor();
        boolean inverse = (color < 0);
        color = Math.abs(color);
        if (!inverse) {
            GL11.glBlendFunc(770, 1);
        } else {
            GL11.glBlendFunc(775, 0);
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)dX, (float)dY + 0.5F, (float)dZ);
        GL11.glRotatef(entity.rotationYaw, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-entity.rotationPitch, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(entity.getRoll(), 0.0F, 0.0F, 1.0F);
        float scale = entity.getSize();
        GL11.glScalef(scale / 2.0F, scale / 2.0F, scale / 2.0F);
        float time = (entity.hitTime != 0L) ? ((float)(entity.hitTime % 6L) + entity.hitStopFactor) : ((float)(entity.worldObj.getWorldTime() % 6L) + f1);
        GL11.glRotatef(time * 60.0F, 0.0F, 1.0F, 0.0F);
        tessellator.startDrawing(4);
        tessellator.setColorRGBA_I(color, 255);
        double dScale = 1.0D;
        for (int idx = 0; idx < nVecPos.length; idx++) {
            tessellator.addVertex(dVec[nVecPos[idx][0]][0] * dScale, dVec[nVecPos[idx][0]][1] * dScale, dVec[nVecPos[idx][0]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][1]][0] * dScale, dVec[nVecPos[idx][1]][1] * dScale, dVec[nVecPos[idx][1]][2] * dScale);
            tessellator.addVertex(dVec[nVecPos[idx][2]][0] * dScale, dVec[nVecPos[idx][2]][1] * dScale, dVec[nVecPos[idx][2]][2] * dScale);
        }
        tessellator.draw();
        GL11.glPopMatrix();
        GL11.glDisable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
    }
}