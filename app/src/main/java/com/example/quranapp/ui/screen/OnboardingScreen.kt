import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quranapp.R

@Composable
fun OnboardingScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clip(RoundedCornerShape(32.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Quran App",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF673AB7),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Text(
                text = "Learn Quran and\nRecite once everyday",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Night sky illustration container
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF673AB7))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                // Stars
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Add small star decorations
                    repeat(5) { index ->
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .background(Color.White.copy(alpha = 0.8f), RoundedCornerShape(2.dp))
                                .align(
                                    when (index) {
                                        0 -> Alignment.TopStart
                                        1 -> Alignment.TopEnd
                                        2 -> Alignment.Center
                                        3 -> Alignment.BottomStart
                                        else -> Alignment.BottomEnd
                                    }
                                )
                        )
                    }
                }

                // Quran illustration
                Image(
                    painter = painterResource(id = R.drawable.quran),
                    contentDescription = "Quran",
                    modifier = Modifier
                        .size(500.dp)
                        .padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Get Started button
            Button(
                onClick = { navController.navigate("quran") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF6B3B) // Coral/peach color
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Get Started",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}