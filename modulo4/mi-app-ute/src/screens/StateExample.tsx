import React from 'react';
import { View, Text, ActivityIndicator, StyleSheet } from 'react-native';
import { LoadState, ProductDTO, successState } from '../types';
import { AppButton } from '../components/AppButton';

export function StateExample() {
  const [state, setState] = React.useState<LoadState<ProductDTO[]>>(successState);
  
  const renderContent = () => {
    // TypeScript INFIERE el tipo exacto basado en 'type'
    switch (state.type) {
      case "idle":
        return <Text style={styles.value}>Presiona cargar para comenzar</Text>;
        
      case "loading":
        return (
          <View style={{ flexDirection: 'row', alignItems: 'center' }}>
            <ActivityIndicator size="small" color="#58a6ff" />
            <Text style={[styles.value, { marginLeft: 10 }]}>Cargando productos...</Text>
          </View>
        );
        
      case "success":
        return (
          <View>
            <Text style={styles.value}>✓ {state.data.length} productos cargados</Text>
            {state.data.map(product => (
              <View key={product.id} style={styles.productItem}>
                <Text style={styles.value}>{product.name}</Text>
                <Text style={styles.label}>${product.price.toFixed(2)}</Text>
              </View>
            ))}
          </View>
        );
        
      case "error":
        return (
          <View>
            <Text style={[styles.value, styles.error]}>✗ {state.message}</Text>
            {state.code && (
              <Text style={styles.label}>Código: {state.code}</Text>
            )}
          </View>
        );
        
      default: {
        // TypeScript asegura que cubrimos todos los casos
        const _exhaustiveCheck: never = state;
        return null;
      }
    }
  };
  
  return (
    <View style={styles.section}>
      <Text style={styles.sectionTitle}>5. Union Types para Estado</Text>
      <View style={styles.card}>
        {renderContent()}
      </View>
      
      <View style={{ flexDirection: 'row', justifyContent: 'space-between', marginTop: 10 }}>
        <AppButton
          title="Idle"
          onPress={() => setState({ type: "idle" })}
          variant="outline"
          style={{ flex: 1, marginRight: 5 }}
        />
        <AppButton
          title="Loading"
          onPress={() => setState({ type: "loading" })}
          variant="outline"
          style={{ flex: 1, marginHorizontal: 5 }}
        />
        <AppButton
          title="Success"
          onPress={() => setState(successState)}
          variant="outline"
          style={{ flex: 1, marginLeft: 5 }}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  section: {
    marginBottom: 20,
  },
  sectionTitle: {
    fontSize: 18,
    fontWeight: 'bold',
    marginBottom: 10,
    color: '#e6edf3',
  },
  card: {
    backgroundColor: '#161b22',
    padding: 15,
    borderRadius: 8,
    borderWidth: 1,
    borderColor: '#30363d',
  },
  value: {
    fontSize: 16,
    color: '#e6edf3',
    marginBottom: 5,
  },
  label: {
    fontSize: 14,
    color: '#8b949e',
  },
  error: {
    color: '#f85149',
  },
  productItem: {
    marginTop: 10,
    paddingTop: 10,
    borderTopWidth: 1,
    borderTopColor: '#30363d',
  },
});
