import { View, Text, StyleSheet } from 'react-native';
import { Link } from 'expo-router';

export default function PublicHome() {
  return (
    <View style={styles.container}>
      <Text style={styles.title}>Bienvenido</Text>
      <Text style={styles.subtitle}>Sistema de Facturación</Text>
      
      <Link href="/public/login" style={styles.link}>
        <Text style={styles.linkText}>Iniciar Sesión</Text>
      </Link>
      
      <Link href="/public/register" style={styles.link}>
        <Text style={styles.linkText}>Registrarse</Text>
      </Link>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    padding: 20,
    backgroundColor: '#f5f5f5',
  },
  title: {
    fontSize: 32,
    fontWeight: 'bold',
    marginBottom: 10,
  },
  subtitle: {
    fontSize: 18,
    color: '#666',
    marginBottom: 40,
  },
  link: {
    backgroundColor: '#6200ee',
    paddingHorizontal: 30,
    paddingVertical: 15,
    borderRadius: 8,
    marginVertical: 10,
  },
  linkText: {
    color: '#fff',
    fontSize: 16,
    fontWeight: '600',
  },
});
