export default function Footer() {
  return (
    <footer className="py-12 px-6 border-t border-[#8da9c4]/10">
      <div className="max-w-7xl mx-auto flex flex-col md:flex-row justify-between items-center gap-6">
        <p className="text-[#636e72] text-sm font-mono">
          © {new Date().getFullYear()} DEV.PORTFOLIO. ALL RIGHTS RESERVED.
        </p>
        <div className="flex space-x-8">
          <a href="#" className="text-xs font-mono uppercase tracking-widest text-[#636e72] hover:text-[#8da9c4] transition-colors">Privacy Policy</a>
          <a href="#" className="text-xs font-mono uppercase tracking-widest text-[#636e72] hover:text-[#8da9c4] transition-colors">Terms of Service</a>
        </div>
      </div>
    </footer>
  );
}
