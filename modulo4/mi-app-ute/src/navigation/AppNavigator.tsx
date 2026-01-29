import { createNativeStackNavigator } from "@react-navigation/native-stack";
import type { RootStackParamList } from "@/navigation/types";
import { HomeScreen } from "@/screens/HomeScreen";
import { DetailsScreen } from "@/screens/DetailsScreen";
import { DemoScreen } from "@/screens/DemoScreen";
import { LoginFormScreen } from "@/screens/LoginFormScreen";
import { AreaScreen } from "@/screens/AreaScreen";
import { SettingsScreen } from "@/screens/SettingsScreen";
import { ProductsListScreen } from "@/screens/ProductsListScreen";
import { ProfileScreen } from "@/screens/ProfileScreen";
import { GlobalCounterScreen } from "@/screens/GlobalCounterScreen";

const Stack = createNativeStackNavigator<RootStackParamList>();

export function AppNavigator() {
  return (
    <Stack.Navigator
      screenOptions={{
        headerStyle: { backgroundColor: "#0d1117" },
        headerTintColor: "#58a6ff",
        headerTitleStyle: { fontWeight: "900" },
        contentStyle: { backgroundColor: "#0d1117" },
      }}
    >
      <Stack.Screen name="Home" component={HomeScreen} options={{ title: "Inicio" }} />
      <Stack.Screen name="Details" component={DetailsScreen} options={{ title: "Detalles" }} />
      <Stack.Screen name="LoginForm" component={LoginFormScreen} options={{ title: "Login Form" }} />
      <Stack.Screen name="DemoScreen" component={DemoScreen} options={{ title: "Demo" }} />
      <Stack.Screen name="AreaScreen" component={AreaScreen} options={{ title: "Calcular Área" }} />
      <Stack.Screen name="Settings" component={SettingsScreen} options={{ title: "Settings" }} />
      <Stack.Screen name="Products" component={ProductsListScreen} options={{ title: "Productos" }} />
      <Stack.Screen name="Profile" component={ProfileScreen} options={{ title: "Perfil" }} />
      <Stack.Screen name="GlobalCounter" component={GlobalCounterScreen} options={{ title: "Global Counter" }} />
    </Stack.Navigator>
  );
}