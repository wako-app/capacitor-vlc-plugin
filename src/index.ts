import { registerPlugin } from '@capacitor/core';

import type { CapacitorVlcPlayerPlugin } from './definitions';

const CapacitorVlcPlayer = registerPlugin<CapacitorVlcPlayerPlugin>('CapacitorVlcPlayer', {
  web: () => import('./web').then((m) => new m.CapacitorVlcPlayerWeb()),
});

export * from './definitions';
export { CapacitorVlcPlayer };
