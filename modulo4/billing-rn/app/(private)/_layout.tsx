import { Redirect, Stack } from 'expo-router';
import { useAuth } from '../../src/auth/AuthContext';

export default function PrivateLayout() {
  const { isAuthenticated, isLoading } = useAuth();

  if (isLoading) {
    return null; // O un componente de loading
  }

  if (!isAuthenticated) {
    return <Redirect href="/public/login" />;
  }

  return (
    <Stack
      screenOptions={{
        headerStyle: { backgroundColor: '#6200ee' },
        headerTintColor: '#fff',
        headerTitleStyle: { fontWeight: 'bold' },
      }}
    />
  );
}
