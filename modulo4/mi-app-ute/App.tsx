import { NavigationContainer } from "@react-navigation/native";
import { AppProvider } from "@/state/app.context";
import { AppNavigator } from "@/navigation/AppNavigator";

export default function App() {
  return (
    <AppProvider>
      <NavigationContainer>
        <AppNavigator />
      </NavigationContainer>
    </AppProvider>
  );
}