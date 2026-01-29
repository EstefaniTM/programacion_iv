import { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import { User, AuthState } from './auth.types';
import { 
  saveToken, 
  saveUser, 
  getToken, 
  getUser, 
  clearAuth 
} from './auth.storage';
import api from '../lib/api';

interface AuthContextType extends AuthState {
  login: (email: string, password: string) => Promise<void>;
  logout: () => Promise<void>;
  register: (name: string, email: string, password: string) => Promise<void>;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

interface AuthProviderProps {
  children: ReactNode;
}

export function AuthProvider({ children }: AuthProviderProps) {
  const [state, setState] = useState<AuthState>({
    user: null,
    token: null,
    isAuthenticated: false,
    isLoading: true,
  });

  // Cargar sesión al iniciar
  useEffect(() => {
    loadStoredAuth();
  }, []);

  const loadStoredAuth = async () => {
    try {
      const [token, user] = await Promise.all([getToken(), getUser()]);
      
      if (token && user) {
        setState({
          user,
          token,
          isAuthenticated: true,
          isLoading: false,
        });
      } else {
        setState(prev => ({ ...prev, isLoading: false }));
      }
    } catch (error) {
      console.error('Error cargando auth:', error);
      setState(prev => ({ ...prev, isLoading: false }));
    }
  };

  const login = async (email: string, password: string) => {
    try {
      // TODO: Cambiar por llamada real al API
      // const response = await api.post('/auth/login', { email, password });
      // const { user, token } = response.data;
      
      // Mock para desarrollo
      const user: User = {
        id: '1',
        name: 'Usuario Demo',
        email: email,
      };
      const token = 'mock-jwt-token';

      await Promise.all([saveToken(token), saveUser(user)]);
      
      setState({
        user,
        token,
        isAuthenticated: true,
        isLoading: false,
      });
    } catch (error) {
      console.error('Error en login:', error);
      throw error;
    }
  };

  const register = async (name: string, email: string, password: string) => {
    try {
      // TODO: Cambiar por llamada real al API
      // const response = await api.post('/auth/register', { name, email, password });
      // const { user, token } = response.data;
      
      // Mock para desarrollo
      const user: User = { id: '1', name, email };
      const token = 'mock-jwt-token';

      await Promise.all([saveToken(token), saveUser(user)]);
      
      setState({
        user,
        token,
        isAuthenticated: true,
        isLoading: false,
      });
    } catch (error) {
      console.error('Error en registro:', error);
      throw error;
    }
  };

  const logout = async () => {
    await clearAuth();
    setState({
      user: null,
      token: null,
      isAuthenticated: false,
      isLoading: false,
    });
  };

  return (
    <AuthContext.Provider
      value={{
        ...state,
        login,
        logout,
        register,
      }}
    >
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth() {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth debe usarse dentro de un AuthProvider');
  }
  return context;
}
